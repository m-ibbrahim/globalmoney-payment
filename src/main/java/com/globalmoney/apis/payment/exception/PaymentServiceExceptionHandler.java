package com.globalmoney.apis.payment.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class PaymentServiceExceptionHandler
{


    @ExceptionHandler(PaymentServiceException.class)
    public ResponseEntity<ExceptionResponseModel> handlePaymentServiceException(PaymentServiceException ex)
    {
        ExceptionResponseModel response = new ExceptionResponseModel();
        response.setCode(ex.getReason());
        response.setDesc(ex.getMessage());

        return new ResponseEntity<>(response, response.getCode().getHttpStatus());
    }
}
