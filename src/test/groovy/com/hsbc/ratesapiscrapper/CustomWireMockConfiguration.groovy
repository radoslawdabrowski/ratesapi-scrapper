package com.hsbc.ratesapiscrapper

import com.github.tomakehurst.wiremock.WireMockServer
import com.hsbc.ratesapiscrapper.client.RatesAPIClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.cloud.openfeign.FeignAutoConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

@EnableConfigurationProperties
@TestConfiguration
@SpringBootTest
@EnableFeignClients(basePackageClasses=RatesAPIClient.class)
@ImportAutoConfiguration([FeignAutoConfiguration.class])
class CustomWireMockConfiguration {

    @Autowired
    private WireMockServer wireMockServer

    @Bean(initMethod = "start", destroyMethod = "stop")
    WireMockServer ratesApiClientService() {
        return new WireMockServer(9561)
    }

}
