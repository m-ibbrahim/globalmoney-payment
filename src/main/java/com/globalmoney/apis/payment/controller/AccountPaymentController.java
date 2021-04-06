package com.globalmoney.apis.payment.controller;

import com.globalmoney.apis.payment.payload.request.AccountPaymentTransferRequest;
import com.globalmoney.apis.payment.service.AccountPaymentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@Api(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@RequestMapping(path = "/payment")
@Slf4j
@Validated
public class AccountPaymentController
{
    @Autowired
    private AccountPaymentService accountPaymentService;

    @PostMapping("/transfer")
    @ApiOperation("API to make account transfers")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Void> accountTransfer(@RequestBody @Valid AccountPaymentTransferRequest request, UriComponentsBuilder builder)
    {
        Long transactionId = accountPaymentService.performAccountPayment(request);

        UriComponents uriComponents = builder.path("/transactionId/{id}").buildAndExpand(transactionId);
        return ResponseEntity.created(uriComponents.toUri()).build();

    }
}
