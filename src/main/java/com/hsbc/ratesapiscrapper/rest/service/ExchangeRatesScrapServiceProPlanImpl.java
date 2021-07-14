package com.hsbc.ratesapiscrapper.rest.service;

import com.hsbc.ratesapiscrapper.client.RatesAPIClient;
import com.hsbc.ratesapiscrapper.client.dto.RatesApiResponseDto;
import com.hsbc.ratesapiscrapper.client.dto.TimeSeriesRequestDto;
import com.hsbc.ratesapiscrapper.client.dto.TimeSeriesResponseDto;
import com.hsbc.ratesapiscrapper.rest.dto.ExchangeRateMapper;
import com.hsbc.ratesapiscrapper.service.RateService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@ConditionalOnProperty(prefix = "external.rates.api", name = "plan", havingValue = "pro")
class ExchangeRatesScrapServiceProPlanImpl extends ExchangeRatesScrapServiceImpl implements ExchangeRatesScrapService {

    public ExchangeRatesScrapServiceProPlanImpl(RatesAPIClient ratesAPIClient, RateService rateService) {
        super(ratesAPIClient, rateService);
    }

    @Override
    public void fetchHistoricalExchangeRatesReport() {
        final LocalDate now = LocalDate.now();
        final LocalDate initialDate = now.minusYears(1);

        TimeSeriesResponseDto responseDto = ratesAPIClient.fetchTimeSeriesData(TimeSeriesRequestDto.builder()
            .start_date(initialDate)
            .end_date(now)
            .build());

        rateService.save(responseDto.getRates());
    }

}
