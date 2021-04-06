package com.globalmoney.apis.payment.service.mapper;

import com.globalmoney.apis.payment.common.PaymentStatus;
import com.globalmoney.apis.payment.common.TransactionType;
import com.globalmoney.apis.payment.payload.request.AccountPaymentTransferRequest;
import com.globalmoney.apis.payment.persistence.entity.AccountTransactions;
import org.springframework.stereotype.Component;

@Component
public class AccountPaymentsMapper
{

    public AccountTransactions toAccountTransactionEntity(AccountPaymentTransferRequest request)
    {
        AccountTransactions transaction = new AccountTransactions();

        transaction.setSourceAccount(request.getSourceAccount());
        transaction.setDestinationAccount(request.getDestinationAccount());
        transaction.setTransactionAmount(request.getAmount());
        transaction.setTransactionType(TransactionType.ACCOUNT_DEBIT);
        transaction.setStatus(PaymentStatus.SUCCESS.getValue());
        transaction.setRemarks(request.getRemarks());
        return transaction;
    }
}
