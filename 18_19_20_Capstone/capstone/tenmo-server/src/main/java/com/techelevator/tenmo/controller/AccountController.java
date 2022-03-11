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
@PreAuthorize("isAuthenticated()")
public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;


    public AccountController(AccountDao accountDao, UserDao userDao){
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @RequestMapping(path = "/account/balance", method = RequestMethod.GET) //creating our endpoint for client-facing access
    public @ResponseBody BigDecimal getUserBalance(Principal principal) {
        int userId = userDao.findIdByUsername(principal.getName());
        return accountDao.getUserBalance(userId); //calling method to get balance, and returning that balance
    }

    //From Read Me:


    //I should be able to choose from a list of users to send TE Bucks to.


}

