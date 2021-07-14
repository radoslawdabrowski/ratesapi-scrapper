package com.hsbc.ratesapiscrapper.rest.dto;

import com.hsbc.ratesapiscrapper.model.Rate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExchangeRateMapper {

    @Mapping(source = "startDate", target = "dataFetchedDate")
    ExchangeRateDto toDto(Rate entity);

}
