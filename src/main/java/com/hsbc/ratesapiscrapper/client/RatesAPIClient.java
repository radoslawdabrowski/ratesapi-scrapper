package com.hsbc.ratesapiscrapper.client;

import com.hsbc.ratesapiscrapper.client.dto.RatesApiResponseDto;
import com.hsbc.ratesapiscrapper.client.dto.TimeSeriesRequestDto;
import com.hsbc.ratesapiscrapper.client.dto.TimeSeriesResponseDto;
import com.hsbc.ratesapiscrapper.model.Symbol;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@FeignClient(value = "rates-api-client", url = "${external.rates.api.baseUrl}")
public interface RatesAPIClient {

    @GetMapping(path = "/timeseries")
    TimeSeriesResponseDto fetchTimeSeriesData(@SpringQueryMap TimeSeriesRequestDto dto);

    @GetMapping(path = "/{date}")
    RatesApiResponseDto fetchHistoricalData(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date, @RequestParam List<Symbol> symbols);

    @GetMapping(path = "/latest")
    RatesApiResponseDto fetchLatest(@RequestParam List<Symbol> symbols);
}
