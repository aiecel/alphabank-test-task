package com.aiecel.alphatesttask.exchange.openexchangerates;

import com.aiecel.alphatesttask.exchange.ExchangeRateProvider;
import com.aiecel.alphatesttask.config.properties.OpenExchangeRatesProperties;
import com.aiecel.alphatesttask.exchange.openexchangerates.client.OpenExchangeRatesClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Класс поставщика валют OpenExchangeRates.
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class OpenExchangeRates implements ExchangeRateProvider {
    private final OpenExchangeRatesProperties properties;
    private final OpenExchangeRatesClient client;
    private OpenExchangeRatesStrategy strategy;

    /**
     * Возвращает курс валюты на текущий момент.
     *
     * @param baseSymbol  символ базовой валюты.
     * @param quoteSymbol символ котируемой валюты.
     * @return курс валюты на текущий момент.
     */
    @Override
    public BigDecimal getExchangeRate(String baseSymbol, String quoteSymbol) {
        return getExchangeRate(LocalDate.now(ZoneId.of("UTC")), baseSymbol, quoteSymbol);
    }

    /**
     * Возвращает курс валюты на заданную дату.
     *
     * @param date        дата.
     * @param baseSymbol  символ базовой валюты.
     * @param quoteSymbol символ котируемой валюты.
     * @return курс валюты на текущий момент.
     */
    @Override
    public BigDecimal getExchangeRate(LocalDate date, String baseSymbol, String quoteSymbol) {
        if (date.isAfter(LocalDate.now(ZoneId.of("UTC"))))
            throw new IllegalArgumentException("Date cannot be in the future");

        if (strategy == null) getOpenExchangeRatesStrategy();

        return strategy.getExchangeRate(client, properties.getAppId(),
                date, baseSymbol.toUpperCase(), quoteSymbol.toUpperCase()
        );
    }

    private void getOpenExchangeRatesStrategy() {
        var usageResponse = client.getUsage(properties.getAppId());

        if (usageResponse.getData().getPlan().getFeatures().isBaseChangeAllowed()) {
            strategy = new NotFixedBaseCurrencyStrategy();
            log.info("Current plan is {} - base currency change is enabled",
                    usageResponse.getData().getPlan().getName()
            );
        } else {
            strategy = new FixedBaseCurrencyStrategy();
            log.info("Current plan is {} - base currency change is disabled",
                    usageResponse.getData().getPlan().getName()
            );
        }
    }
}
