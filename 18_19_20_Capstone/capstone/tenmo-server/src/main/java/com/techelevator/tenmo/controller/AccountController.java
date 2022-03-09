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
    private UserDao userDao;

    public AccountController(AccountDao dao){
        this.accountDao = dao;
        this.userDao = userDao;
    }

    @PreAuthorize("permitAll")
    @RequestMapping(path = "balance/{userId}", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable int userId) throws AccountNotFoundException {
        BigDecimal balance = accountDao.getUserBalance(userId);
        return balance;
    }






}

