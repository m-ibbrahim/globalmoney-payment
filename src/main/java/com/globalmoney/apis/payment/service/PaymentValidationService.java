package com.globalmoney.apis.payment.service;

import com.globalmoney.apis.payment.payload.request.AccountPaymentTransferRequest;
import com.globalmoney.apis.payment.persistence.entity.AccountDetails;

import java.util.Optional;

public interface PaymentValidationService
{
    public void validateAccountPaymentTransfer(Optional<AccountDetails> sourceAccount, Optional<AccountDetails> destinationAccount, AccountPaymentTransferRequest request);
}
