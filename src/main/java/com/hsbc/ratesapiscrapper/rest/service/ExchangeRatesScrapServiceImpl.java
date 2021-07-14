package com.hsbc.ratesapiscrapper.rest.service;

import com.hsbc.ratesapiscrapper.client.RatesAPIClient;
import com.hsbc.ratesapiscrapper.service.RateService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class ExchangeRatesScrapServiceImpl {

    final RatesAPIClient ratesAPIClient;
    final RateService rateService;

}
