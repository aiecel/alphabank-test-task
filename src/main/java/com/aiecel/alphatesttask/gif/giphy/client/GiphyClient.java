package com.aiecel.alphatesttask.gif.giphy.client;

import com.aiecel.alphatesttask.gif.giphy.Rating;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Клиент для API Giphy.
 */
@FeignClient(name = "giphyClient", url = "${giphy.url}")
public interface GiphyClient {
    /**
     * Возвращает случайную gif с заданным тегом и рейтингом.
     *
     * @param apiKey apiKey.
     * @param tag    тег.
     * @param rating рейтинг.
     * @return {@link RandomGifResponse}
     */
    @GetMapping("/gifs/random")
    RandomGifResponse getRandom(@RequestParam("api_key") String apiKey,
                                @RequestParam String tag,
                                @RequestParam Rating rating);
}
