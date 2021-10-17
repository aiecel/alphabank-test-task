package com.aiecel.alphatesttask.exchange.openexchangerates.client;

import lombok.Value;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;

/**
 * Ответ {@link OpenExchangeRatesClient} на запрос курсов валют ({@link OpenExchangeRatesClient#getHistorical}).
 */
@Value
public class ExchangeRatesResponse {
    Instant timestamp;
    String base;
    Map<String, BigDecimal> rates;
}
