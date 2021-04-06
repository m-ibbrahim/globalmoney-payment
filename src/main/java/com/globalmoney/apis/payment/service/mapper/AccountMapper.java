package com.globalmoney.apis.payment.service.mapper;

import com.globalmoney.apis.payment.payload.response.AccountDetailsResponse;
import com.globalmoney.apis.payment.persistence.entity.AccountDetails;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper
{

    public AccountDetailsResponse toAccountDetailsResponse(AccountDetails accountDetails)
    {

        return AccountDetailsResponse
                .builder()
                .accountNumber(accountDetails.getAccountNumber())
                .accountType(accountDetails.getAccountType())
                .accountCurrency(accountDetails.getAccountCurrency())
                .currentBalance(accountDetails.getCurrentBalance())
                .createdOn(accountDetails.getCreatedAt())
                .lastModifiedOn(accountDetails.getModifiedAt())
                .build();
    }
}
