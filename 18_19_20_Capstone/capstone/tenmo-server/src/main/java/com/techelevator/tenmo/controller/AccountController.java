package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

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

    //As an authenticated user of the system, I need to be able to be able to transfer money to a list of users.
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> getListOfUsers(){
        return userDao.findAll();
    }

    //we know this isn't as secure, but could not figure out how else to do it.
    //As an authenticated user of the system, I need to be able to be able to transfer money to a list of users.
   @RequestMapping(path = "/user/getAccount", method = RequestMethod.GET)
    public Account getAccountByUsername(String username){
        return accountDao.findAccountByName(username);
   }
}

