package com.ekar.demo.app;

import com.ekar.demo.app.config.AppProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class EkarAssignmentApplication {

    public static void main(String[] args) {
        log.debug("No. of CPU cores: {}", Runtime.getRuntime().availableProcessors());
        SpringApplication.run(EkarAssignmentApplication.class, args);
    }

}
