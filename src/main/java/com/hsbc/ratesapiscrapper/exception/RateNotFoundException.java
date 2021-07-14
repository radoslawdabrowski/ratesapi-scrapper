package com.hsbc.ratesapiscrapper.exception;

import com.hsbc.ratesapiscrapper.model.Symbol;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Getter
@RequiredArgsConstructor
public class RateNotFoundException extends RuntimeException {

    private final LocalDate date;
    private final Symbol symbol;

}
