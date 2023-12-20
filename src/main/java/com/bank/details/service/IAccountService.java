package com.bank.details.service;


import com.bank.details.model.AccountDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface IAccountService {

    void createAccount(List<AccountDetails> accountDetails);


    AccountDetails getCurrentBalance(UUID accountId);
}
