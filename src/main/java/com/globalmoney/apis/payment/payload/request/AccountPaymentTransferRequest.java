package com.globalmoney.apis.payment.payload.request;

import com.globalmoney.apis.payment.common.Currency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@ApiModel
@Getter
@Setter
@Validated
public class AccountPaymentTransferRequest
{
    @NotNull
    @ApiModelProperty(value = "Customer Id", example = "100")
    private Long customerId;

    @NotNull
    @ApiModelProperty(value = "Source Account", example = "1001")
    private Long sourceAccount;

    @NotNull
    @ApiModelProperty(value = "Destination Account", example = "1002")
    private Long destinationAccount;

    @ApiModelProperty(value = "Payment amount", example = "100")
    private double amount;

    @ApiModelProperty(value = "Currency", example = "AED")
    private Currency currency;

    @ApiModelProperty(value = "Remarks", example = "Remarks")
    private String remarks;
}
