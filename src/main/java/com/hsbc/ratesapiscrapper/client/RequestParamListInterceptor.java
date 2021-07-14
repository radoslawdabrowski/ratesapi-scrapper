package com.hsbc.ratesapiscrapper.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RequestParamListInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        var queries = template.queries().entrySet().stream().collect(Collectors.toMap(
            Map.Entry::getKey,
            entry -> {
                if (entry.getValue().size() > 1) {
                    return List.of(String.join(",", entry.getValue()));
                }
                return entry.getValue();
            }
        ));

        template.queries(null);
        template.queries(queries);
    }
}
