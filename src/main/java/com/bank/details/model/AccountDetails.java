package com.bank.details.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "account")
@Data
public class AccountDetails {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="account_id")
    private UUID accountId;

    private double currentBalance;

    private String bankName;

    private String ownerName;

    @OneToMany(mappedBy = "account")
    private transient List<Transaction> transactions;


}
