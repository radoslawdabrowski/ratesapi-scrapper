package com.hsbc.ratesapiscrapper.rest.service;

import com.hsbc.ratesapiscrapper.model.Symbol;
import com.hsbc.ratesapiscrapper.rest.dto.ExchangeRateDto;
import com.hsbc.ratesapiscrapper.rest.dto.ExchangeRateMapper;
import com.hsbc.ratesapiscrapper.service.RateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private final ExchangeRateMapper exchangeRateMapper;
    private final RateService rateService;

    @Override
    public ExchangeRateDto getRate(LocalDate date, Symbol symbol) {
        return exchangeRateMapper.toDto(rateService.getRateByDateAndSymbol(date, symbol));
    }

    @Override
    public Map<LocalDate, List<ExchangeRateDto>> getRates(LocalDate dateFrom, LocalDate dateTo) {
        Map<LocalDate, List<ExchangeRateDto>> collection = new LinkedHashMap<>();

        rateService.getRatesBetweenDates(dateFrom, dateTo).forEach(item -> {
            collection.computeIfAbsent(item.getStartDate(), k -> new ArrayList<>());
            collection.get(item.getStartDate()).add(exchangeRateMapper.toDto(item));
        });

        return collection;
    }

}
