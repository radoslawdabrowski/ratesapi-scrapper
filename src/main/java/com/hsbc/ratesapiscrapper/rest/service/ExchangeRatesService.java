package com.hsbc.ratesapiscrapper.rest.service;

import com.hsbc.ratesapiscrapper.model.Symbol;
import com.hsbc.ratesapiscrapper.rest.dto.ExchangeRateDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ExchangeRatesService {

    ExchangeRateDto getRate(LocalDate date, Symbol symbol);
    Map<LocalDate, List<ExchangeRateDto>> getRates(LocalDate dateFrom, LocalDate dateTo);

}
