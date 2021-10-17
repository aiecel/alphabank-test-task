package com.aiecel.alphatesttask.exchange.openexchangerates;

import com.aiecel.alphatesttask.exception.UnknownCurrencyException;
import com.aiecel.alphatesttask.exchange.openexchangerates.client.OpenExchangeRatesClient;
import feign.FeignException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

/**
 * Класс стратегии запроса курсов валют {@link OpenExchangeRates}.
 * Используется, когда изменение базовой валюты запрещено (тариф Free).
 */
final class FixedBaseCurrencyStrategy implements OpenExchangeRatesStrategy {
    public static final String FIXED_BASE_CURRENCY = "USD";
    public static final int DIVISION_SCALE = 5;

    /**
     * Возвращает курс валюты на определённую дату.
     *
     * @param client      ссылка на {@link OpenExchangeRatesClient}
     * @param appId       appId.
     * @param date        дата.
     * @param baseSymbol  символ базовой валюты.
     * @param quoteSymbol символ котируемой валюты.
     * @return курс валюты на определённую дату.
     */
    @Override
    public BigDecimal getExchangeRate(OpenExchangeRatesClient client, String appId,
                                      LocalDate date, String baseSymbol, String quoteSymbol) {
        if (baseSymbol.equals(FIXED_BASE_CURRENCY)) {
            return getExchangeRateWithDefaultBase(client, appId, date, quoteSymbol);
        }

        if (quoteSymbol.equals(FIXED_BASE_CURRENCY)) {
            return BigDecimal.ONE.divide(getExchangeRateWithDefaultBase(client, appId, date, baseSymbol),
                    DIVISION_SCALE, RoundingMode.HALF_EVEN);
        }

        var baseCurrencyRate = getExchangeRateWithDefaultBase(client, appId, date, baseSymbol);
        var currencyRate = getExchangeRateWithDefaultBase(client, appId, date, quoteSymbol);

        return currencyRate.divide(baseCurrencyRate, DIVISION_SCALE, RoundingMode.HALF_EVEN);
    }

    private BigDecimal getExchangeRateWithDefaultBase(OpenExchangeRatesClient client, String appId,
                                                      LocalDate date, String quoteCurrency) {
        try {
            var response = client.getHistorical(date, appId, FIXED_BASE_CURRENCY, quoteCurrency);

            if (!response.getRates().containsKey(quoteCurrency.toUpperCase()))
                throw new UnknownCurrencyException(quoteCurrency);

            return response.getRates().get(quoteCurrency.toUpperCase());
        } catch (FeignException.BadRequest e) {
            throw new IllegalArgumentException("Invalid date: " + date);
        } catch (FeignException.Forbidden e) {
            throw new IllegalArgumentException("Invalid fixed base currency : " + FIXED_BASE_CURRENCY);
        }
    }
}
