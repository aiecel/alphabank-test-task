package com.aiecel.alphatesttask.gif;

/**
 * Интерфейс для поставщиков gif.
 */
public interface GifProvider {
    /**
     * Возвращает случайную gif с заданным тегом.
     *
     * @param tag тег.
     * @return gif.
     */
    byte[] getRandomGif(String tag);
}
