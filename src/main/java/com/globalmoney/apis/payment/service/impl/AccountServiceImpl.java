package com.globalmoney.apis.payment.service.impl;

import com.globalmoney.apis.payment.exception.ExceptionReason;
import com.globalmoney.apis.payment.exception.PaymentServiceException;
import com.globalmoney.apis.payment.payload.response.AccountDetailsResponse;
import com.globalmoney.apis.payment.persistence.entity.AccountDetails;
import com.globalmoney.apis.payment.persistence.repository.AccountRepository;
import com.globalmoney.apis.payment.service.AccountService;
import com.globalmoney.apis.payment.service.mapper.AccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class AccountServiceImpl implements AccountService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper mapper;

    /**
     * Method returns the account details, if account number doesn't exist in DB then throws 404
     *
     * @param accountNumber
     * @return
     */
    @Override
    public AccountDetailsResponse getAccountDetails(Long accountNumber)
    {

        LOGGER.info("getting account details");
        Optional<AccountDetails> account = accountRepository.findById(accountNumber);
        if (account.isPresent()) {
            return mapper.toAccountDetailsResponse(account.get());
        }
        else {
            throw new PaymentServiceException("Account doesn't exist for account number: " + accountNumber, ExceptionReason.RESOURCE_NOT_FOUND);
        }
    }

}
