package com.bank.details.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@Data
@SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_sequence", schema = "online_bank", initialValue = 5)
public class Transaction {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="TX_ID")
    private UUID id;

    private double amount;

    private Date transactionDate;

    private String text;

    private String transactionType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private AccountDetails accountDetails;

}
