package com.globalmoney.apis.payment.service;

import com.globalmoney.apis.payment.exception.PaymentServiceException;
import com.globalmoney.apis.payment.helper.MockedPayloads;
import com.globalmoney.apis.payment.payload.response.AccountDetailsResponse;
import com.globalmoney.apis.payment.persistence.entity.AccountDetails;
import com.globalmoney.apis.payment.persistence.repository.AccountRepository;
import com.globalmoney.apis.payment.service.impl.AccountServiceImpl;
import com.globalmoney.apis.payment.service.mapper.AccountMapper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest
{

    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountMapper accountMapper;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    public static final long ACCOUNT_NUMBER = 101l;

    @Test
    public void testGetAccountDetails()
    {

        AccountDetails accountDetails = MockedPayloads.getAccountDetailsEntity();
        AccountDetailsResponse mockedResponse = MockedPayloads.getAccountDetailsResponse();
        when(accountRepository.findById(ACCOUNT_NUMBER)).thenReturn(Optional.of(accountDetails));
        when(accountMapper.toAccountDetailsResponse(accountDetails)).thenReturn(mockedResponse);

        AccountDetailsResponse methodResponse = accountService.getAccountDetails(ACCOUNT_NUMBER);

        assertEquals(methodResponse.getAccountNumber(), mockedResponse.getAccountNumber());
        assertEquals(methodResponse.getAccountType(), mockedResponse.getAccountType());
        assertEquals(methodResponse.getCurrentBalance(), mockedResponse.getCurrentBalance(), 0);
        verify(accountRepository, times(1)).findById(ACCOUNT_NUMBER);
        verify(accountMapper, times(1)).toAccountDetailsResponse(accountDetails);
    }

    @Test
    public void testGetAccountDetails_Exception()
    {

        expectedException.expectMessage("Account doesn't exist for account number: " + ACCOUNT_NUMBER);
        expectedException.expect(PaymentServiceException.class);
        AccountDetails accountDetails = MockedPayloads.getAccountDetailsEntity();
        AccountDetailsResponse mockedResponse = MockedPayloads.getAccountDetailsResponse();
        when(accountRepository.findById(ACCOUNT_NUMBER)).thenReturn(Optional.empty());
        when(accountMapper.toAccountDetailsResponse(accountDetails)).thenReturn(mockedResponse);
        accountService.getAccountDetails(ACCOUNT_NUMBER);
    }


}
