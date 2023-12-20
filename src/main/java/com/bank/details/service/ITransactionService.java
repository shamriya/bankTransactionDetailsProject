package com.bank.details.service;

import com.bank.details.model.AccountDetails;
import com.bank.details.model.Transaction;
import com.bank.details.model.Transfer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public interface ITransactionService {

    void createTransactionPayment(Transaction transaction);

    void createTransactionTransfer(Transaction transaction);

    Map getSubsequentTransaction(String s);

    List<AccountDetails> findNegativeAmount(List<AccountDetails> accountDetails);



}
