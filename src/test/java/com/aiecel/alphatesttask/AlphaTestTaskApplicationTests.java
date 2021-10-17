package com.aiecel.alphatesttask;

import com.aiecel.alphatesttask.config.properties.RichBrokeProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableConfigurationProperties
class AlphaTestTaskApplicationTests {
    @Autowired
    private RichBrokeProperties properties;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void richbrokegifUSD_returnsValidResponse() {
        var responseStr = restTemplate.getForObject(
                "http://localhost:" + port + "/richbrokegif?symbol=USD",
                String.class
        );

        Assertions.assertTrue(
                responseStr.contains(properties.getRateUpTag()) || responseStr.contains(properties.getRateDownTag())
        );
    }
}
