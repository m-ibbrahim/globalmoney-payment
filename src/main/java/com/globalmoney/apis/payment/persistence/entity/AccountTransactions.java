package com.globalmoney.apis.payment.persistence.entity;

import com.globalmoney.apis.payment.common.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_TRANSACTIONS")
@Getter
@Setter
public class AccountTransactions extends BaseEntity
{
    private static final long serialVersionUID = -4671894364767714553L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_TRANSACTION_ID")
    @SequenceGenerator(name = "SEQ_TRANSACTION_ID", allocationSize = 1)
    private Long id;

    private String remarks;

    private double transactionAmount;

    private Long sourceAccount;

    private Long destinationAccount;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private int status;

}
