package com.hsbc.ratesapiscrapper.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessKeyInterceptor implements RequestInterceptor {

    private final RatesAPIConfiguration.RatesApiPropertiesFactory ratesApiPropertiesFactory;

    @Override
    public void apply(RequestTemplate template) {
        template.query("access_key", ratesApiPropertiesFactory.getAccessKey());
    }
}
