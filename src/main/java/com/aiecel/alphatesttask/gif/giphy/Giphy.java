package com.aiecel.alphatesttask.gif.giphy;

import com.aiecel.alphatesttask.config.properties.GiphyProperties;
import com.aiecel.alphatesttask.exception.GifDownloadException;
import com.aiecel.alphatesttask.gif.WebGifProvider;
import com.aiecel.alphatesttask.gif.giphy.client.GiphyClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * Класс поставщика gif Giphy.
 */
@Component
@RequiredArgsConstructor
public class Giphy implements WebGifProvider {
    private final GiphyProperties properties;
    private final GiphyClient client;

    /**
     * Возвращает случайную gif с заданным тегом.
     *
     * @param tag тег.
     * @return gif.
     */
    @Override
    public byte[] getRandomGif(String tag) {
        return getBytesFromGifUrl(getRandomGifURL(tag));
    }

    /**
     * Возвращает ссылку на случайную gif с заданным тегом.
     *
     * @param tag тег.
     * @return ссылка на gif.
     */
    @Override
    public String getRandomGifURL(String tag) {
        return client.getRandom(properties.getApiKey(), tag, properties.getRating())
                .getData().getUrl();
    }

    private byte[] getBytesFromGifUrl(String url) {
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(url).openStream());
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {

            byte[] chunk = new byte[1024];
            int n;

            while ((n = bufferedInputStream.read(chunk)) > 0) {
                byteArrayOutputStream.write(chunk, 0, n);
            }

            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new GifDownloadException("Unable to get gif from " + url);
        }
    }
}
