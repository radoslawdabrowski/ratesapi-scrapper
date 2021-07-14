package com.hsbc.ratesapiscrapper.repository;

import com.hsbc.ratesapiscrapper.model.Rate;
import com.hsbc.ratesapiscrapper.model.Symbol;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RateRepository extends CrudRepository<Rate, UUID> {

    Optional<Rate> findRateByStartDateBeforeAndEndDateAfterAndSymbol(LocalDate startDate, LocalDate endDate, Symbol symbol);
    List<Rate> findAllByStartDateBetween(LocalDate dateFrom, LocalDate dateTo);
    Rate save(Rate rate);

}
