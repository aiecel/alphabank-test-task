package com.aiecel.alphatesttask.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * Класс параметров для {@link com.aiecel.alphatesttask.service.RichBrokeGifServiceImpl}.
 */
@Validated
@ConfigurationProperties(prefix = "richbroke")
@Getter
@Setter
public class RichBrokeProperties {
    @NotBlank
    private String baseCurrency = "RUB";

    @NotBlank
    private String rateUpTag = "rich";

    @NotBlank
    private String rateDownTag = "broke";
}
