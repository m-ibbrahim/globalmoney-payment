package com.globalmoney.apis.payment.persistence.entity;

import com.globalmoney.apis.payment.common.AccountTypes;
import com.globalmoney.apis.payment.common.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ACCOUNT_DETAILS")
@Getter
@Setter
public class AccountDetails extends BaseEntity
{
    private static final long serialVersionUID = 792105238433777524L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCOUNT_NUMBER")
    @SequenceGenerator(name = "SEQ_ACCOUNT_NUMBER", allocationSize = 1)
    @Column(name = "ACCOUNT_NUMBER")
    private Long accountNumber;

    @Enumerated(EnumType.STRING)
    private Currency accountCurrency;

    @Enumerated(EnumType.STRING)
    private AccountTypes accountType;

    private double currentBalance;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerDetails customer;

}
