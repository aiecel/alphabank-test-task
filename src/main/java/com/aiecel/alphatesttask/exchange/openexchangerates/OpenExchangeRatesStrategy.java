package com.aiecel.alphatesttask.exchange.openexchangerates;

import com.aiecel.alphatesttask.exchange.openexchangerates.client.OpenExchangeRatesClient;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс стратегии запроса курсов валют {@link OpenExchangeRates}
 */
interface OpenExchangeRatesStrategy {
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
    BigDecimal getExchangeRate(OpenExchangeRatesClient client, String appId,
                               LocalDate date, String baseSymbol, String quoteSymbol);
}
