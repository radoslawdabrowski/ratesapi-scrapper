package com.hsbc.ratesapiscrapper.service;

import com.hsbc.ratesapiscrapper.model.Rate;
import com.hsbc.ratesapiscrapper.model.Symbol;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RateService {

    void save(Map<LocalDate, Map<Symbol, Double>> rates);

    Rate getRateByDateAndSymbol(LocalDate date, Symbol symbol);

    List<Rate> getRatesBetweenDates(LocalDate dateFrom, LocalDate dateTo);

}
