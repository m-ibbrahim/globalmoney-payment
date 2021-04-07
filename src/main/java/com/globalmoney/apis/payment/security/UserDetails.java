package com.globalmoney.apis.payment.security;

import java.io.Serializable;

public class UserDetails implements Serializable
{
    private final String userId;

    public UserDetails(String userId)
    {
        this.userId = userId;
    }
}
