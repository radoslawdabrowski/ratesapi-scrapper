package com.hsbc.ratesapiscrapper.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    public static ErrorResponse of(String message, Object... params) {
        return new ErrorResponse(String.format(message, params));
    }

}
