package com.hsbc.ratesapiscrapper.client.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class TimeSeriesRequestDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate start_date;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate end_date;

}
