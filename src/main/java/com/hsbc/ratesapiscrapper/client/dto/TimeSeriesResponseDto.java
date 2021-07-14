package com.hsbc.ratesapiscrapper.client.dto;

import com.hsbc.ratesapiscrapper.model.Symbol;
import lombok.Data;

import java.time.LocalDate;
import java.util.Map;

@Data
public class TimeSeriesResponseDto {

    private Symbol base;
    private Map<LocalDate, Map<Symbol, Double>> rates;

}
