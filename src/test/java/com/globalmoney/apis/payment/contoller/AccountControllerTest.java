package com.globalmoney.apis.payment.contoller;

import com.globalmoney.apis.payment.controller.AccountController;
import com.globalmoney.apis.payment.payload.response.AccountDetailsResponse;
import com.globalmoney.apis.payment.service.impl.AccountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mohamamd Uzair
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AccountControllerTest
{

    private MockMvc mockMvc;

    @InjectMocks
    private AccountController controller;

    @Mock
    private AccountServiceImpl accountService;

    public static final Long ACCOUNT_ID = 10001l;

    @Before
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void getAccountDetails() throws Exception
    {

        when(accountService.getAccountDetails(ACCOUNT_ID)).thenReturn(mockAccountDetailsResponse());
        mockMvc.perform(MockMvcRequestBuilders.get("/account/{account-number}", ACCOUNT_ID))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.accountNumber").value(mockAccountDetailsResponse().getAccountNumber()))
                .andExpect(jsonPath("$.currentBalance").value(mockAccountDetailsResponse().getCurrentBalance()));


    }

    @Test
    public void getAccountDetails_404() throws Exception
    {

        when(accountService.getAccountDetails(ACCOUNT_ID)).thenReturn(mockAccountDetailsResponse());
        mockMvc.perform(MockMvcRequestBuilders.get("/accounts/"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));

    }

    private AccountDetailsResponse mockAccountDetailsResponse()
    {

        return AccountDetailsResponse.builder()
                .accountNumber(ACCOUNT_ID)
                .currentBalance(1000l)
                .build();
    }


}
