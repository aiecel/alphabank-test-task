package com.aiecel.alphatesttask.exchange;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Интерфейс для поставщиков курсов валют.
 */
public interface ExchangeRateProvider {
    /**
     * Возвращает курс валюты на текущий момент.
     *
     * @param baseSymbol  символ базовой валюты.
     * @param quoteSymbol символ котируемой валюты.
     * @return курс валюты на текущий момент.
     */
    BigDecimal getExchangeRate(String baseSymbol, String quoteSymbol);

    /**
     * Возвращает курс валюты на заданную дату.
     *
     * @param date        дата.
     * @param baseSymbol  символ базовой валюты.
     * @param quoteSymbol символ котируемой валюты.
     * @return курс валюты на текущий момент.
     */
    BigDecimal getExchangeRate(LocalDate date, String baseSymbol, String quoteSymbol);
}
