package com.bank.details.controller;

import com.bank.details.model.AccountDetails;
import com.bank.details.model.Payment;
import com.bank.details.model.Transaction;
import com.bank.details.model.Transfer;
import com.bank.details.service.ITransactionService;
import com.bank.details.service.impl.TransactionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
/**
 * @Author Shamriya Nandalapadu
 * @Description transaction controller handles task related webservices
 **/
public class TransactionController {

    @Autowired
    ITransactionService transactionService;

    @RequestMapping(value = "/createPayment", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createPayment (@Valid @RequestBody Payment transaction) {
        transactionService.createTransactionPayment(transaction);
    }

    @RequestMapping(value = "/createTransfer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createTransfer(@Valid @RequestBody Transfer transaction) {
        transactionService.createTransactionPayment(transaction);
    }


    @RequestMapping(value = "/getSubsequentTransactiom", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
        public Map getSubsequentTransaction(@RequestParam("subsequenctVale") String subsequenctVale) {
          return  transactionService.getSubsequentTransaction(subsequenctVale);
        }


    @RequestMapping(value = "/findNegativeAmount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void findNegativeAmount(@Valid @RequestBody List<AccountDetails> accountDetails) {
        transactionService.findNegativeAmount(accountDetails);
    }
}
