package com.hsbc.ratesapiscrapper.client;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationPropertiesScan(value = "com.hsbc.ratesapiscrapper")
@RequiredArgsConstructor
@EnableFeignClients
class RatesAPIConfiguration {

    @Data
    @RequiredArgsConstructor
    @ConfigurationProperties(prefix = "external.rates.api")
    static class RatesApiPropertiesFactory {
        private String baseUrl;
        private String accessKey;
    }
}
