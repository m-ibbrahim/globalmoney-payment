package com.globalmoney.apis.payment.payload.response;

import com.globalmoney.apis.payment.common.AccountTypes;
import com.globalmoney.apis.payment.common.Currency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel("Model for get account details response")
@Getter
@Setter
@Builder
public class AccountDetailsResponse implements Serializable
{
    private static final long serialVersionUID = -632521984762897700L;

    @ApiModelProperty("Account Number")
    private Long accountNumber;

    @ApiModelProperty("Account Type")
    private AccountTypes accountType;

    @ApiModelProperty("Current Balance")
    private double currentBalance;

    @ApiModelProperty("Account Currency")
    private Currency accountCurrency;

    @ApiModelProperty("Created On")
    private LocalDateTime createdOn;

    @ApiModelProperty("Last Modified On")
    private LocalDateTime lastModifiedOn;

}
