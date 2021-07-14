package com.hsbc.ratesapiscrapper.rest.service;

import com.hsbc.ratesapiscrapper.client.RatesAPIClient;
import com.hsbc.ratesapiscrapper.client.dto.RatesApiResponseDto;
import com.hsbc.ratesapiscrapper.model.Symbol;
import com.hsbc.ratesapiscrapper.service.RateService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;

@Service
@ConditionalOnProperty(prefix = "external.rates.api", name = "plan", havingValue = "free")
class ExchangeRatesScrapServiceFreePlanImpl extends ExchangeRatesScrapServiceImpl implements ExchangeRatesScrapService {

    public ExchangeRatesScrapServiceFreePlanImpl(RatesAPIClient ratesAPIClient, RateService rateService) {
        super(ratesAPIClient, rateService);
    }

    @Override
    public void fetchHistoricalExchangeRatesReport() {
        final Map<LocalDate, Map<Symbol, Double>> rates = new LinkedHashMap<>();

        final LocalDate now = LocalDate.now();
        final LocalDate initialDate = now.minusYears(1);

        for (LocalDate date = initialDate; date.isBefore(now); date = date.plusMonths(1)) {
            LocalDate firstDayOfTheMonthDate = date.with(firstDayOfMonth());
            RatesApiResponseDto responseDto = ratesAPIClient.fetchHistoricalData(firstDayOfTheMonthDate, Symbol.getDefaultSymbols());
            rates.put(firstDayOfTheMonthDate, responseDto.getRates());
        }

        rateService.save(rates);
    }

}
