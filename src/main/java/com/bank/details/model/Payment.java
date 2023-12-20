package com.bank.details.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@Entity
public class Payment extends Transaction{

    @NotNull
    private String recipientNumber;

}
