package com.aiecel.alphatesttask.api.dto;

import com.aiecel.alphatesttask.service.RichBrokeGifService;
import lombok.Value;

/**
 * DTO, возвращаемый {@link RichBrokeGifService}.
 */
@Value
public class RichBrokeGifDTO {
    String tag;
    String gifUrl;
}
