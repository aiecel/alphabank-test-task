package com.aiecel.alphatesttask.api;

import com.aiecel.alphatesttask.api.dto.RichBrokeGifDTO;
import com.aiecel.alphatesttask.validation.CurrencySymbol;
import com.aiecel.alphatesttask.service.RichBrokeGifService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Контроллер для {@link RichBrokeGifService}
 */
@RestController
@Validated
@RequestMapping("api/richbrokegif")
@RequiredArgsConstructor
public class RichBrokeGifController {
    private final RichBrokeGifService richBrokeGifService;

    /**
     * Возвращает тег и ссылку на gif в зависимости от изменения курса валюты.
     *
     * @param symbol символ валюты.
     * @return {@link RichBrokeGifDTO}.
     */
    @GetMapping()
    public RichBrokeGifDTO getDTO(@RequestParam(defaultValue = "USD") @CurrencySymbol String symbol) {
        return richBrokeGifService.getDTODependingOnExchangeRateChange(symbol);
    }

    /**
     * Возвращает gif в зависимости от изменения курса валюты.
     *
     * @param symbol символ валюты.
     * @return gif.
     */
    @GetMapping("/get")
    public byte[] getGif(@RequestParam(defaultValue = "USD") @CurrencySymbol String symbol) {
        return richBrokeGifService.getGifBytesDependingOnExchangeRateChange(symbol);
    }

    /**
     * Перенаправляет на gif в зависимости от изменения курса валюты.
     *
     * @param symbol символ валюты.
     */
    @GetMapping("/redirect")
    public void redirect(@RequestParam(defaultValue = "USD") @CurrencySymbol String symbol,
                         HttpServletResponse response) throws IOException {
        response.sendRedirect(richBrokeGifService.getDTODependingOnExchangeRateChange(symbol).getGifUrl());
    }
}
