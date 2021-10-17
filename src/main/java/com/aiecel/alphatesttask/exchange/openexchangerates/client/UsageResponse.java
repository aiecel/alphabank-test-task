package com.aiecel.alphatesttask.exchange.openexchangerates.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

/**
 * Ответ {@link OpenExchangeRatesClient} на запрос информации об аккаунте ({@link OpenExchangeRatesClient#getUsage}).
 */
@Value
public class UsageResponse {
    Data data;

    @JsonCreator
    public UsageResponse(Data data) {
        this.data = data;
    }

    @Value
    public static class Data {
        Plan plan;

        @JsonCreator
        public Data(Plan plan) {
            this.plan = plan;
        }

        @Value
        public static class Plan {
            String name;
            Features features;

            @JsonCreator
            public Plan(String name, Features features) {
                this.name = name;
                this.features = features;
            }

            @Value
            public static class Features {
                boolean baseChangeAllowed;

                @JsonCreator
                public Features(@JsonProperty("base") boolean baseChangeAllowed) {
                    this.baseChangeAllowed = baseChangeAllowed;
                }
            }
        }
    }
}
