package com.aiecel.alphatesttask.config.properties;

import com.aiecel.alphatesttask.gif.giphy.Rating;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Класс параметров для {@link com.aiecel.alphatesttask.gif.giphy.Giphy}.
 */
@Validated
@ConfigurationProperties(prefix = "giphy")
@Getter
@Setter
public class GiphyProperties {
    @URL
    private String url;

    @NotBlank
    private String apiKey;

    @NotNull
    private Rating rating = Rating.G;
}
