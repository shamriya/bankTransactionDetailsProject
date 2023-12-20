package com.bank.details.controller;


import com.bank.details.model.AccountDetails;
import com.bank.details.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
/**
 * @Author Shamriya Nandalapadu
 * @Description Account controller handles task related webservices
 **/
public class AccountDetailsController {

    @Autowired
    IAccountService accountService;


    @RequestMapping(value = "/createAccount", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createAccount(@Valid @RequestBody List<AccountDetails> accountDetails) {
        accountService.createAccount(accountDetails);
    }


    @RequestMapping(value = "/getCurrentBalance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public AccountDetails getCurrentBalance(@Valid @RequestBody UUID accountId) {
       return accountService.getCurrentBalance(accountId);
    }

}
