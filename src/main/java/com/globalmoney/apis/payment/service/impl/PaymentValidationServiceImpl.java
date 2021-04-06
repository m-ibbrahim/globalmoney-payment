package com.globalmoney.apis.payment.service.impl;

import com.globalmoney.apis.payment.common.PaymentConfig;
import com.globalmoney.apis.payment.exception.ExceptionReason;
import com.globalmoney.apis.payment.exception.PaymentServiceException;
import com.globalmoney.apis.payment.payload.request.AccountPaymentTransferRequest;
import com.globalmoney.apis.payment.persistence.entity.AccountDetails;
import com.globalmoney.apis.payment.service.PaymentValidationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class PaymentValidationServiceImpl implements PaymentValidationService
{

    @Autowired
    private PaymentConfig paymentConfig;


    /**
     * @param sourceAccount
     * @param destinationAccount
     * @param request            This method will perform that if the payment request is allowed to proceed
     */
    @Override
    public void validateAccountPaymentTransfer(Optional<AccountDetails> sourceAccount,
                                               Optional<AccountDetails> destinationAccount,
                                               AccountPaymentTransferRequest request)
    {


        // validate if source account exist
        if (!sourceAccount.isPresent()) {
            throw new PaymentServiceException("Source Account Doesn't exist", ExceptionReason.INVALID_SOURCE_ACCOUNT);
        }

        // Validate if source account belongs to the customer
        AccountDetails srcAccount = sourceAccount.get();
        if (!srcAccount.getCustomer().getCustomerId().equals(request.getCustomerId())) {
            throw new PaymentServiceException("Source Account doesn't belongs to customer", ExceptionReason.INVALID_SOURCE_ACCOUNT);

        }
        // validate if destination account exist
        if (!destinationAccount.isPresent()) {
            throw new PaymentServiceException("Destination Account Doesn't exist", ExceptionReason.INVALID_DESTINATION_ACCOUNT);
        }

        // validate min transfer amount
        if (request.getAmount() < paymentConfig.getAccount().getMinTransferAmount()) {
            throw new PaymentServiceException("Requested Transfer amount in less than the min transfer limit", ExceptionReason.INVALID_TRANSFER_AMOUNT);
        }

        // validate if
        if (request.getAmount() > sourceAccount.get().getCurrentBalance()) {
            throw new PaymentServiceException("Insufficient Balance in Source account", ExceptionReason.INSUFFICIENT_BALANCE);

        }

    }

}
