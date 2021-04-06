package com.globalmoney.apis.payment.service;

import com.globalmoney.apis.payment.payload.request.AccountPaymentTransferRequest;

public interface AccountPaymentService
{
    public Long performAccountPayment(AccountPaymentTransferRequest requests);
}
