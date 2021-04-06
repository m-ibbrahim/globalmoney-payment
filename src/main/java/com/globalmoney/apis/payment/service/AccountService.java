package com.globalmoney.apis.payment.service;

import com.globalmoney.apis.payment.payload.response.AccountDetailsResponse;

public interface AccountService
{

    public AccountDetailsResponse getAccountDetails(Long accountNumber);
}
