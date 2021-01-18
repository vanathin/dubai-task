package com.ekar.demo.app.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "app")
@Configuration
public class AppProperties {
    private int counterUpperBound;
    private int counterLowerBound;
    private String producerPrefix;
    private String consumerPrefix;
    private Long consumerProcessingTime;
    private Long producerProcessingTime;
}
