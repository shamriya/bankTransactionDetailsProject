package com.bank.details.model;


import lombok.Data;

@Data
public class TransactionDetails {

    private Transaction transaction;

    private int monthWise;

    private int weekWise;
}
