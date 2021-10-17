package com.aiecel.alphatesttask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@ConfigurationPropertiesScan("com.aiecel.alphatesttask.config")
@EnableFeignClients
public class AlphaTestTaskApplication {
	public static void main(String[] args) {
		SpringApplication.run(AlphaTestTaskApplication.class, args);
	}
}
