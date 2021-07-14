package com.hsbc.ratesapiscrapper.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Symbol {
    GBP("GBP"),
    HKD("HKD"),
    USD("USD"),
    EUR("EUR");

    private final String name;

    @Override
    public String toString() {
        return name;
    }

    public static List<Symbol> getDefaultSymbols() {
        return List.of(GBP, USD, HKD);
    }

}
