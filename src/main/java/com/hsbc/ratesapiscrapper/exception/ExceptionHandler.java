package com.hsbc.ratesapiscrapper.exception;

import feign.FeignException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
@Order(1)
public class ExceptionHandler {

    /**
     * todo Basically message should be translated form i18n bundle.
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    @org.springframework.web.bind.annotation.ExceptionHandler(FeignException.class)
    public ErrorResponse handleClientExceptionHandler() {
        return ErrorResponse.of("Fetching data from external api failed");
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @org.springframework.web.bind.annotation.ExceptionHandler(RateNotFoundException.class)
    public ErrorResponse rateNotFoundExceptionHandler(RateNotFoundException exception) {
        return ErrorResponse.of("Rate for symbol %s not found for date %s",
            exception.getSymbol(), exception.getDate());
    }

    /**
     * todo handle saving records in db in prettier way
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.CONFLICT)
    @org.springframework.web.bind.annotation.ExceptionHandler(SQLException.class)
    private ErrorResponse savingInDbExceptionHandler(SQLException exception) {
        return ErrorResponse.of(exception.getMessage());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @org.springframework.web.bind.annotation.ExceptionHandler(MissingRequestValueException.class)
    private ErrorResponse badRequestExceptionHandler() {
        return ErrorResponse.of("Required parameters not found");
    }

}
