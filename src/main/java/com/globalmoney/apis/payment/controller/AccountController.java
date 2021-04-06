package com.globalmoney.apis.payment.controller;

import com.globalmoney.apis.payment.payload.response.AccountDetailsResponse;
import com.globalmoney.apis.payment.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Api(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Slf4j
public class AccountController
{
    @Autowired
    private AccountService accountService;

    @ApiOperation("API to get accounts")
    @GetMapping("/{account-number}")
    public ResponseEntity<AccountDetailsResponse> getAccountDetails(@PathVariable("account-number") Long accountNumber)
    {
        return ResponseEntity.ok(accountService.getAccountDetails(accountNumber));
    }
}
