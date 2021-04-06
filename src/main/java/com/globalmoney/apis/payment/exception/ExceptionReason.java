package com.globalmoney.apis.payment.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ExceptionReason
{
    INVALID_DESTINATION_ACCOUNT(HttpStatus.BAD_REQUEST),
    INVALID_SOURCE_ACCOUNT(HttpStatus.BAD_REQUEST),
    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST),
    INVALID_TRANSFER_AMOUNT(HttpStatus.BAD_REQUEST),
    RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND);

    private HttpStatus httpStatus;
}
