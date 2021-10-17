package com.aiecel.alphatesttask.exchange.openexchangerates.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

/**
 * Клиент для API OpenExchangeRates.
 */
@FeignClient(value = "openExchangeRates", url = "${openexchangerates.url}")
public interface OpenExchangeRatesClient {
    /**
     * Возвращает информацию о тарифе.
     *
     * @param appId appId.
     * @return {@link UsageResponse}.
     */
    @GetMapping("/usage.json")
    UsageResponse getUsage(@RequestParam("app_id") String appId);

    /**
     * Возвращает курс валют на определённую дату.
     *
     * @param date         дата.
     * @param appId        appId.
     * @param baseSymbol   символ базовой валюты.
     * @param quoteSymbols символы котируемых валют.
     * @return {@link ExchangeRatesResponse}.
     */
    @GetMapping("/historical/{date}.json")
    ExchangeRatesResponse getHistorical(@PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                        @RequestParam("app_id") String appId,
                                        @RequestParam("base") String baseSymbol,
                                        @RequestParam("symbols") String... quoteSymbols);
}
