package com.globalmoney.apis.payment.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel(description = "Error response model fro PaymentService")
@NoArgsConstructor
public class ExceptionResponseModel
{
    @ApiModelProperty
    private ExceptionReason code;

    @ApiModelProperty
    private String desc;

    @ApiModelProperty
    private LocalDateTime timestamp = LocalDateTime.now();

}
