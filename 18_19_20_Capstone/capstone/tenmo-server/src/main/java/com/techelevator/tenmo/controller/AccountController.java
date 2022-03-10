package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.security.Principal;

@RestController
public class AccountController {

    private AccountDao accountDao;


    public AccountController(AccountDao dao){
        this.accountDao = dao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "balance/{userId}", method = RequestMethod.GET) //creating our endpoint for client-facing access
    public BigDecimal getBalance(@PathVariable int userId) {
        return accountDao.getUserBalance(userId); //calling method to get balance, and returning that balance
    }

    //From Read Me:


    //I should be able to choose from a list of users to send TE Bucks to.


}

