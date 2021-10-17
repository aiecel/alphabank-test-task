package com.aiecel.alphatesttask.service;

import com.aiecel.alphatesttask.api.dto.RichBrokeGifDTO;
import com.aiecel.alphatesttask.config.properties.RichBrokeProperties;
import com.aiecel.alphatesttask.exchange.ExchangeRateProvider;
import com.aiecel.alphatesttask.gif.giphy.Giphy;
import com.aiecel.alphatesttask.gif.giphy.Rating;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest(classes = RichBrokeGifServiceImpl.class)
@EnableConfigurationProperties(RichBrokeProperties.class)
class RichBrokeGifServiceImplTest {
    @MockBean
    Giphy mockGifProvider;

    @MockBean
    ExchangeRateProvider mockExchangeRateProvider;

    @Autowired
    RichBrokeGifServiceImpl gifService;

    @Test
    void getDTODependingOnExchangeRateChangeUSD_whenRateChangedUp_returnsCorrectRichDTO() {
        var baseCurrency = "RUB";
        var currency = "USD";

        var yesterdayRate = new BigDecimal(1);
        var todayRate = new BigDecimal(2);

        var tag = "rich";
        var gifUrl = "https://url.test/";

        Mockito.when(mockExchangeRateProvider.getExchangeRate(
                LocalDate.now().minusDays(1), baseCurrency, currency)).thenReturn(yesterdayRate);

        Mockito.when(mockExchangeRateProvider.getExchangeRate(baseCurrency, currency)).thenReturn(todayRate);
        Mockito.when(mockGifProvider.getRandomGifURL(tag)).thenReturn(gifUrl);

        var expected = new RichBrokeGifDTO(tag, gifUrl);

        Assertions.assertEquals(expected, gifService.getDTODependingOnExchangeRateChange(currency));
    }

    @Test
    void getDTODependingOnExchangeRateChangeUSD_whenRateChangedDown_returnsCorrectBrokeDTO() {
        var baseCurrency = "RUB";
        var currency = "USD";

        var yesterdayRate = new BigDecimal(2);
        var todayRate = new BigDecimal(1);

        var tag = "broke";
        var gifUrl = "https://url.test/";

        Mockito.when(mockExchangeRateProvider.getExchangeRate(
                LocalDate.now().minusDays(1), baseCurrency, currency)).thenReturn(yesterdayRate);

        Mockito.when(mockExchangeRateProvider.getExchangeRate(baseCurrency, currency)).thenReturn(todayRate);
        Mockito.when(mockGifProvider.getRandomGifURL(tag)).thenReturn(gifUrl);

        var expected = new RichBrokeGifDTO(tag, gifUrl);

        Assertions.assertEquals(expected, gifService.getDTODependingOnExchangeRateChange(currency));
    }
}