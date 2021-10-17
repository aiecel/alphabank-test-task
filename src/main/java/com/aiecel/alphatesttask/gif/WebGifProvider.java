package com.aiecel.alphatesttask.gif;

/**
 * Интерфейс для поставщиков gif из Интернета.
 */
public interface WebGifProvider extends GifProvider {
    /**
     * Возвращает ссылку на случайную gif с заданным тегом.
     *
     * @param tag тег.
     * @return ссылка на gif.
     */
    String getRandomGifURL(String tag);
}
