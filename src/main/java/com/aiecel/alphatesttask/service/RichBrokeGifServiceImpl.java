package com.aiecel.alphatesttask.service;

import com.aiecel.alphatesttask.api.dto.RichBrokeGifDTO;
import com.aiecel.alphatesttask.config.properties.RichBrokeProperties;
import com.aiecel.alphatesttask.gif.WebGifProvider;
import com.aiecel.alphatesttask.exchange.ExchangeRateProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class RichBrokeGifServiceImpl implements RichBrokeGifService {
    private final RichBrokeProperties richBrokeProperties;
    private final ExchangeRateProvider exchangeRateProvider;
    private final WebGifProvider gifProvider;

    /**
     * Возвращает gif в зависимости от изменения курса валюты за день.
     *
     * @param symbol символ валюты.
     * @return gif.
     */
    @Override
    public byte[] getGifBytesDependingOnExchangeRateChange(String symbol) {
        return gifProvider.getRandomGif(getTagDependingOnExchangeRateChange(symbol));
    }

    /**
     * Возвращает тег и ссылку на gif в зависимости от изменения курса валюты за день.
     *
     * @param symbol символ валюты.
     * @return {@link RichBrokeGifDTO}
     */
    @Override
    public RichBrokeGifDTO getDTODependingOnExchangeRateChange(String symbol) {
        var tag = getTagDependingOnExchangeRateChange(symbol);
        var url = gifProvider.getRandomGifURL(tag);
        return new RichBrokeGifDTO(tag, url);
    }

    private String getTagDependingOnExchangeRateChange(String symbol) {
        var todayExchangeRate = exchangeRateProvider.getExchangeRate(
                richBrokeProperties.getBaseCurrency(), symbol
        );

        var yesterdayExchangeRate = exchangeRateProvider.getExchangeRate(
                LocalDate.now(ZoneId.of("UTC")).minusDays(1),
                richBrokeProperties.getBaseCurrency(), symbol
        );

        return todayExchangeRate.compareTo(yesterdayExchangeRate) > 0 ?
                richBrokeProperties.getRateUpTag() : richBrokeProperties.getRateDownTag();
    }
}
