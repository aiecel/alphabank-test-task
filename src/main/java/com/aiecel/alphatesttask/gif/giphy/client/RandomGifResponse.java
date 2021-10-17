package com.aiecel.alphatesttask.gif.giphy.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * Ответ {@link GiphyClient} на запрос курсов валют ({@link GiphyClient#getRandom}).
 */
@Value
public class RandomGifResponse {
    Gif data;

    @JsonCreator
    public RandomGifResponse(Gif data) {
        this.data = data;
    }

    @Value
    public static class Gif {
        String url;

        @JsonCreator
        public Gif(@JsonProperty("image_original_url") String url) {
            this.url = url;
        }
    }
}
