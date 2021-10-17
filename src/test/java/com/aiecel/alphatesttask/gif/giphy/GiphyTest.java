package com.aiecel.alphatesttask.gif.giphy;

import com.aiecel.alphatesttask.config.properties.GiphyProperties;
import com.aiecel.alphatesttask.gif.giphy.client.GiphyClient;
import com.aiecel.alphatesttask.gif.giphy.client.RandomGifResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = Giphy.class)
@EnableConfigurationProperties(GiphyProperties.class)
class GiphyTest {
    @Autowired
    GiphyProperties testProperties;

    @MockBean
    GiphyClient mockGiphyClient;

    @Autowired
    Giphy giphy;

    @Test
    void getRandomGifUrl_returnsCorrectUrl() {
        var tag = "test";
        var url = "https://url.test/";

        Mockito.when(mockGiphyClient.getRandom(testProperties.getApiKey(), tag, testProperties.getRating()))
                .thenReturn(new RandomGifResponse(new RandomGifResponse.Gif(url)));

        Assertions.assertEquals(url, giphy.getRandomGifURL(tag));
    }
}