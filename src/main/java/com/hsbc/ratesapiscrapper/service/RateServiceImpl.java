package com.hsbc.ratesapiscrapper.service;

import com.hsbc.ratesapiscrapper.exception.RateNotFoundException;
import com.hsbc.ratesapiscrapper.model.Rate;
import com.hsbc.ratesapiscrapper.model.Symbol;
import com.hsbc.ratesapiscrapper.repository.RateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Service
@RequiredArgsConstructor
public class RateServiceImpl implements RateService {

    private final RateRepository rateRepository;

    @Override
    public void save(final Map<LocalDate, Map<Symbol, Double>> rateGroups) {
        rateGroups.forEach((date, rates)
            -> rates.forEach((symbol, rate)
                -> save(Rate.builder()
                    .fetchedDate(LocalDate.now())
                    .symbol(symbol)
                    .startDate(date.with(firstDayOfMonth()))
                    .endDate(date.with(lastDayOfMonth()))
                    .rate(rate)
                    .build())));
    }

    @Override
    public Rate getRateByDateAndSymbol(LocalDate date, Symbol symbol) {
        return rateRepository.findRateByStartDateBeforeAndEndDateAfterAndSymbol(date, date, symbol)
            .orElseThrow(() -> new RateNotFoundException(date, symbol));
    }

    @Override
    public List<Rate> getRatesBetweenDates(LocalDate dateFrom, LocalDate dateTo) {
        return rateRepository.findAllByStartDateBetween(dateFrom, dateTo);
    }

    private Rate save(Rate rate) {
        return rateRepository.save(rate);
    }
}
