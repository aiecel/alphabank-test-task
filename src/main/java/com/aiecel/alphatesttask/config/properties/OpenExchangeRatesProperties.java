package com.aiecel.alphatesttask.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * Класс параметров для {@link com.aiecel.alphatesttask.exchange.openexchangerates.OpenExchangeRates}.
 */
@Validated
@ConfigurationProperties(prefix = "openexchangerates")
@Getter
@Setter
public class OpenExchangeRatesProperties {
    @URL
    private String url;

    @NotBlank
    private String appId;
}
