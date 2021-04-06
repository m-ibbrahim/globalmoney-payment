package com.globalmoney.apis.payment.helper;

import com.globalmoney.apis.payment.common.AccountTypes;
import com.globalmoney.apis.payment.payload.request.AccountPaymentTransferRequest;
import com.globalmoney.apis.payment.payload.response.AccountDetailsResponse;
import com.globalmoney.apis.payment.persistence.entity.AccountDetails;
import com.globalmoney.apis.payment.persistence.entity.AccountTransactions;

public class MockedPayloads
{

    public static AccountPaymentTransferRequest getPaymentRequest()
    {
        AccountPaymentTransferRequest request = new AccountPaymentTransferRequest();
        request.setCustomerId(100l);
        request.setAmount(100l);
        request.setSourceAccount(1001l);
        request.setDestinationAccount(1002l);
        return request;
    }

    public static AccountDetails getAccountDetailsEntity()
    {
        AccountDetails acc = new AccountDetails();
        acc.setCurrentBalance(1000l);
        acc.setAccountNumber(101l);
        acc.setAccountType(AccountTypes.SAVINGS);
        return acc;
    }

    public static AccountDetailsResponse getAccountDetailsResponse()
    {
        return AccountDetailsResponse.builder()
                .currentBalance(1000l)
                .accountNumber(101l)
                .accountType(AccountTypes.SAVINGS)
                .build();
    }

    public static AccountTransactions getAccountTransactionEntity()
    {
        AccountTransactions transaction = new AccountTransactions();

        transaction.setId(1001l);
        transaction.setStatus(1);
        return transaction;

    }

}
