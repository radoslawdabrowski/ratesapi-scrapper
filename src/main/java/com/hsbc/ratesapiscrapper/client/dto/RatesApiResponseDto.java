package com.hsbc.ratesapiscrapper.client.dto;

import com.hsbc.ratesapiscrapper.model.Symbol;
import lombok.Data;
import java.util.Map;

@Data
public class RatesApiResponseDto {

    private boolean success;
    private Symbol base;
    private Map<Symbol, Double> rates;

}
