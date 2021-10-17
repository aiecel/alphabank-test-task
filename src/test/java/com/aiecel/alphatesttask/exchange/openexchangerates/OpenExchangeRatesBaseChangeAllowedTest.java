package com.aiecel.alphatesttask.exchange.openexchangerates;

import com.aiecel.alphatesttask.config.properties.OpenExchangeRatesProperties;
import com.aiecel.alphatesttask.exchange.openexchangerates.client.ExchangeRatesResponse;
import com.aiecel.alphatesttask.exchange.openexchangerates.client.OpenExchangeRatesClient;
import com.aiecel.alphatesttask.exchange.openexchangerates.client.UsageResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;

@SpringBootTest(classes = OpenExchangeRates.class)
@EnableConfigurationProperties(OpenExchangeRatesProperties.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
class OpenExchangeRatesBaseChangeAllowedTest {
    @Autowired
    OpenExchangeRatesProperties testProperties;

    @MockBean
    OpenExchangeRatesClient mockOpenExchangeRatesClient;

    @Autowired
    OpenExchangeRates openExchangeRates;

    @BeforeAll
    public void setup() {
        Mockito.when(mockOpenExchangeRatesClient.getUsage(testProperties.getAppId())).thenReturn(
                new UsageResponse(new UsageResponse.Data(new UsageResponse.Data.Plan(
                        "Test plan",
                        new UsageResponse.Data.Plan.Features(true)
                ))));
    }

    @Test
    void getExchangeRateUSDRUB_returnsCorrectRate() {
        var baseCurrency = "USD";
        var currency = "RUB";
        var rate = new BigDecimal(70);
        var rates = new HashMap<String, BigDecimal>();
        rates.put(currency, rate);

        Mockito.when(mockOpenExchangeRatesClient.getHistorical(
                        LocalDate.now(ZoneId.of("UTC")),
                        testProperties.getAppId(),
                        baseCurrency,
                        currency))
                .thenReturn(new ExchangeRatesResponse(Instant.now(), baseCurrency, rates));

        var actual = openExchangeRates.getExchangeRate(baseCurrency, currency);

        Assertions.assertEquals(rate, actual);
    }

    @Test
    void getExchangeRateRUBUSD_returnsCorrectRate() {
        var baseCurrency = "RUB";
        var currency = "USD";

        var rate = new BigDecimal("0.014");
        var rates = new HashMap<String, BigDecimal>();
        rates.put(currency, rate);

        Mockito.when(mockOpenExchangeRatesClient.getHistorical(
                        LocalDate.now(ZoneId.of("UTC")),
                        testProperties.getAppId(),
                        baseCurrency,
                        currency))
                .thenReturn(new ExchangeRatesResponse(Instant.now(), baseCurrency, rates));

        var actual = openExchangeRates.getExchangeRate(baseCurrency, currency);

        Assertions.assertEquals(rate, actual);
    }

    @Test
    void getExchangeRateRUBJPY_returnsCorrectRate() {
        var baseCurrency = "RUB";
        var currency = "JPY";

        var rate = new BigDecimal("1.6");
        var rates = new HashMap<String, BigDecimal>();
        rates.put(currency, rate);

        Mockito.when(mockOpenExchangeRatesClient.getHistorical(
                        LocalDate.now(ZoneId.of("UTC")),
                        testProperties.getAppId(),
                        baseCurrency,
                        currency))
                .thenReturn(new ExchangeRatesResponse(Instant.now(), baseCurrency, rates));

        var actual = openExchangeRates.getExchangeRate(baseCurrency, currency);

        Assertions.assertEquals(rate, actual);
    }

    @Test
    void getExchangeRate_whenDateIsInTheFuture_throwsIllegalArgumentException() {
        var baseCurrency = "USD";
        var currency = "RUB";
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> openExchangeRates.getExchangeRate(
                        LocalDate.now(ZoneId.of("UTC")).plusDays(1),
                        baseCurrency,
                        currency
                )
        );
    }
}