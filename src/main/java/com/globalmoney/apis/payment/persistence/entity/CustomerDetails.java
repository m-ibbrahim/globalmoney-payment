package com.globalmoney.apis.payment.persistence.entity;

import com.globalmoney.apis.payment.common.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER_DETAILS")
@Getter
@Setter
public class CustomerDetails extends BaseEntity
{
    private static final long serialVersionUID = 8887101398822362433L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CUSTOMER_ID")
    @SequenceGenerator(name = "SEQ_CUSTOMER_ID", allocationSize = 1)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;

    private String title;

    private String firstName;

    private String middleName;

    private String LastName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL, mappedBy = "customer")
    private List<AccountDetails> accountDetails;

}
