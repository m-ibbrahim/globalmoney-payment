package com.globalmoney.apis.payment.contoller;

import com.globalmoney.apis.payment.controller.AccountPaymentController;
import com.globalmoney.apis.payment.helper.MockedPayloads;
import com.globalmoney.apis.payment.payload.request.AccountPaymentTransferRequest;
import com.globalmoney.apis.payment.service.impl.AccountPaymentServiceImpl;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mohamamd Uzair
 */

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class AccountPaymentControllerTest
{

    private MockMvc mockMvc;

    @InjectMocks
    private AccountPaymentController controller;

    @Mock
    private AccountPaymentServiceImpl paymentService;

    public static final String ACCOUNT_TRANSFER_PATH = "/payment/transfer";


    @Before
    public void setup()
    {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void accountTransferSuccess() throws Exception
    {

        Gson gson = new Gson();

        AccountPaymentTransferRequest request = MockedPayloads.getPaymentRequest();
        when(paymentService.performAccountPayment(request)).thenReturn(111l);
        mockMvc.perform(MockMvcRequestBuilders.post(ACCOUNT_TRANSFER_PATH)
                .content(gson.toJson(request))
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().is(HttpStatus.CREATED.value()));
        verify(paymentService, times(1)).performAccountPayment(any(AccountPaymentTransferRequest.class));

    }

    @Test
    public void accountTransferSuccess_validate_location_header() throws Exception
    {

        Gson gson = new Gson();

        when(paymentService.performAccountPayment(any(AccountPaymentTransferRequest.class))).thenReturn(111l);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(ACCOUNT_TRANSFER_PATH)
                .content(gson.toJson(MockedPayloads.getPaymentRequest()))
                .contentType(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        assertTrue(result.getResponse().getHeader("Location").contains("111"));


    }
}
