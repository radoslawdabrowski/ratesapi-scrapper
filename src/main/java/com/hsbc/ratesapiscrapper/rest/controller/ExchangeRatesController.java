package com.hsbc.ratesapiscrapper.rest.controller;

import com.hsbc.ratesapiscrapper.model.Symbol;
import com.hsbc.ratesapiscrapper.rest.dto.ExchangeRateDto;
import com.hsbc.ratesapiscrapper.rest.service.ExchangeRatesScrapService;
import com.hsbc.ratesapiscrapper.rest.service.ExchangeRatesService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/rates")
class ExchangeRatesController {

    private final ExchangeRatesScrapService exchangeRatesScrapService;
    private final ExchangeRatesService exchangeRatesService;

    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void fetchHistoricalRates() {
        exchangeRatesScrapService.fetchHistoricalExchangeRatesReport();
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    Map<LocalDate, List<ExchangeRateDto>> getRates(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFrom,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateTo
    ) {
        return exchangeRatesService.getRates(dateFrom, dateTo);
    }

    @GetMapping("/{date}")
    @ResponseStatus(HttpStatus.OK)
    ExchangeRateDto getRateForDate(
        @PathVariable @Valid @NotNull @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @RequestParam(required = false, defaultValue = "GBP") Symbol symbol
    ) {
        return exchangeRatesService.getRate(date, symbol);
    }
}
