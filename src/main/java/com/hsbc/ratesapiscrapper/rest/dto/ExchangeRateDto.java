package com.hsbc.ratesapiscrapper.rest.dto;

import com.hsbc.ratesapiscrapper.model.Symbol;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExchangeRateDto {

    private LocalDate dataFetchedDate;
    private Symbol symbol;
    private Double rate;

}
