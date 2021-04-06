package com.globalmoney.apis.payment.service;

import com.globalmoney.apis.payment.helper.MockedPayloads;
import com.globalmoney.apis.payment.payload.request.AccountPaymentTransferRequest;
import com.globalmoney.apis.payment.persistence.entity.AccountDetails;
import com.globalmoney.apis.payment.persistence.entity.AccountTransactions;
import com.globalmoney.apis.payment.persistence.repository.AccountRepository;
import com.globalmoney.apis.payment.persistence.repository.AccountTransactionsRepository;
import com.globalmoney.apis.payment.service.impl.AccountPaymentServiceImpl;
import com.globalmoney.apis.payment.service.impl.PaymentValidationServiceImpl;
import com.globalmoney.apis.payment.service.mapper.AccountPaymentsMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class AccountPaymentServiceTest
{

    private static final Long SOURCE_ACCOUNT = 100l;
    private static final Long DESTINATION_ACCOUNNT = 101l;

    @InjectMocks
    private AccountPaymentServiceImpl paymentService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private PaymentValidationServiceImpl paymentValidationService;

    @Mock
    private AccountTransactionsRepository transactionsRepository;

    @Mock
    private AccountPaymentsMapper paymentsMapper;

    @Test
    public void testPerformAccountPayment()
    {
        AccountDetails sourceAccount = MockedPayloads.getAccountDetailsEntity();
        AccountDetails destinationAccount = MockedPayloads.getAccountDetailsEntity();
        AccountPaymentTransferRequest request = MockedPayloads.getPaymentRequest();
        AccountTransactions transactions = MockedPayloads.getAccountTransactionEntity();

        sourceAccount.setAccountNumber(SOURCE_ACCOUNT);
        destinationAccount.setAccountNumber(DESTINATION_ACCOUNNT);
        request.setSourceAccount(SOURCE_ACCOUNT);
        request.setDestinationAccount(DESTINATION_ACCOUNNT);

        when(accountRepository.findById(SOURCE_ACCOUNT))
                .thenReturn(Optional.of(sourceAccount));
        when(accountRepository.findById(DESTINATION_ACCOUNNT))
                .thenReturn(Optional.of(destinationAccount));
        doNothing().when(paymentValidationService)
                .validateAccountPaymentTransfer(any(), any(), any());
        when(transactionsRepository.save(any(AccountTransactions.class)))
                .thenReturn(transactions);
        when(paymentsMapper.toAccountTransactionEntity(any(AccountPaymentTransferRequest.class)))
                .thenReturn(transactions);

        Long transactionId = paymentService.performAccountPayment(request);
        assertEquals(transactionId, transactions.getId(), 0);
    }
}
