package com.bank.details.service.impl;

import com.bank.details.model.AccountDetails;
import com.bank.details.model.Transaction;
import com.bank.details.repository.AccountRepository;
import com.bank.details.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements IAccountService {


    @Autowired
    AccountRepository accountRepository;

    @Override
    public void createAccount(List<AccountDetails> accountDetails) {
        accountRepository.saveAll(accountDetails);
    }

    public List<AccountDetails> findAccountDetails(List<Transaction> transactions){
        List<UUID> accountIds = transactions.stream().map(transaction -> transaction.getId()).collect(Collectors.toList());
       return  accountRepository.findAllByUUIDId(accountIds);
    }

    public AccountDetails findAccountDetailsById(UUID accountId){
         return accountRepository.findByAccountNumber(accountId);
    }

    public void updateCurrentAmount(AccountDetails accountDetails){
        accountRepository.save(accountDetails);
    }


    /* During Transaction balance will be updated we can get the balance here*/
    @Override
    public AccountDetails getCurrentBalance(UUID accountId) {
        return accountRepository.findByAccountNumber(accountId);
    }


}
