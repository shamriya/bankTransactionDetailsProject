package com.bank.details.service.impl;


import com.bank.details.model.AccountDetails;
import com.bank.details.model.Transaction;
import com.bank.details.model.TransactionDetails;
import com.bank.details.repository.AccountRepository;
import com.bank.details.repository.TransactionRepository;
import com.bank.details.service.IAccountService;
import com.bank.details.service.ITransactionService;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
@Slf4j
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    TransactionRepository transactionRepository;



    @Autowired
    AccountServiceImpl accountService;

    /* During Transaction balance will be updated*/
    @Transactional
    @Override
    public void createTransactionPayment(Transaction transaction) {
       AccountDetails accountDetails =  accountService.findAccountDetailsById(transaction.
               getAccountDetails().getAccountId());
        //if -ve amount it will substract else add the amount to balance
        accountDetails.setCurrentBalance(accountDetails.getCurrentBalance()+transaction.getAmount());
        accountService.updateCurrentAmount(accountDetails);
        //set the account details for transaction
        transaction.setAccountDetails(accountDetails);
        transactionRepository.save(transaction);
    }

    /* During Transaction balance will be updated*/
    @Transactional
    @Override
    public void createTransactionTransfer(Transaction transaction) {
        AccountDetails accountDetails =  accountService.findAccountDetailsById
                (transaction.getAccountDetails().getAccountId());
        //if -ve amount it will substract else add the amount to balance
        accountDetails.setCurrentBalance(accountDetails.getCurrentBalance()+transaction.getAmount());
        accountService.updateCurrentAmount(accountDetails);
        //set the account details for transaction
        transaction.setAccountDetails(accountDetails);
        transactionRepository.save(transaction);
    }

    @Override
    public Map getSubsequentTransaction(String s) {
        List<Transaction> transactions = new ArrayList<>();
        transactions =transactionRepository.findAll();
        // Group transactions by month
        Map<Integer, List<Transaction>> subTransactions= new HashMap<>();
        switch(s){
            case "Monthly":
                subTransactions=getMonthWiseTransactions(transactions);
                break;
            case "Weekly":
                subTransactions = getWeekWiseTransactions(transactions);
        }
        return subTransactions;
    }


    @Transactional
    @Override
    public List<AccountDetails> findNegativeAmount(List<AccountDetails> accountDetails) {
        List<Transaction> transactions =  transactionRepository.findNegativeTransaction();
        List<AccountDetails> accountDetailsList = accountService.findAccountDetails(transactions);
        return accountDetailsList;
    }


    private static Map<Integer, List<Transaction>> getMonthWiseTransactions(List<Transaction> transactions) {
        Map<Integer, List<Transaction>> monthWiseTransactions = new HashMap<>();

        for (Transaction transaction : transactions) {
            // Get the month from the transaction date
            int month = transaction.getTransactionDate().getMonth() + 1; // Month is 0-based in Date class

            // Add the transaction to the corresponding month's list
            monthWiseTransactions.computeIfAbsent(month, k -> new ArrayList<>()).add(transaction);
        }

        return monthWiseTransactions;
    }

    private static Map<Integer, List<Transaction>> getWeekWiseTransactions(List<Transaction> transactions) {
        Map<Integer, List<Transaction>> weekWiseTransactions = new HashMap<>();

        Calendar calendar = Calendar.getInstance();

        for (Transaction transaction : transactions) {
            calendar.setTime(transaction.getTransactionDate());

            // Get the week of the year from the transaction date
            int week = calendar.get(Calendar.WEEK_OF_YEAR);

            // Add the transaction to the corresponding week's list
            weekWiseTransactions.computeIfAbsent(week, k -> new ArrayList<>()).add(transaction);
        }

        return weekWiseTransactions;
    }


}
