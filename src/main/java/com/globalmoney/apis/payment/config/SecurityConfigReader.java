package com.globalmoney.apis.payment.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "security")
public class SecurityConfigReader
{
    private String appSecretKey;

    public SecurityConfigReader()
    {
    }

    public String getAppSecretKey()
    {
        return this.appSecretKey;
    }

    public void setAppSecret(final String appSecretKey)
    {
        this.appSecretKey = appSecretKey;
    }
}
