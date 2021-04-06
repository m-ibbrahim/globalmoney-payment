package com.globalmoney.apis.payment.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "payments")
@Getter
@Setter
public class PaymentConfig
{

    private Account account;

    @Getter
    @Setter
    public static class Account
    {
        private double minTransferAmount;
    }


}
