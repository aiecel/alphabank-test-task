package com.aiecel.alphatesttask.service;

import com.aiecel.alphatesttask.api.dto.RichBrokeGifDTO;

/**
 * Интерфейс для сервисов, возвращающих gif в зависимости от изменения курсов валют.
 */
public interface RichBrokeGifService {
    /**
     * Возвращает gif в зависимости от изменения курса валюты за день.
     *
     * @param symbol символ валюты.
     * @return gif.
     */
    byte[] getGifBytesDependingOnExchangeRateChange(String symbol);

    /**
     * Возвращает тег и ссылку на gif в зависимости от изменения курса валюты за день.
     *
     * @param symbol символ валюты.
     * @return {@link RichBrokeGifDTO}
     */
    RichBrokeGifDTO getDTODependingOnExchangeRateChange(String symbol);
}
