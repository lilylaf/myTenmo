package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private TransferDao transferDao;
    private UserDao userDao;


    //As an authenticated user of the system, I need to be able to see transfers I have sent or received.
    @RequestMapping(path = "account/transfer", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers(Principal principal){ //using Principal for better security
        int userId = userDao.findIdByUsername(principal.getName()); //utilizing our userDao to find our user
        return transferDao.getListOfTransfers(userId); //returning a list of the users' transactions
    }

    //As an authenticated user of the system, I need to be able to retrieve the details of any transfer based upon the transfer ID.
    @RequestMapping(path ="account/transfer/{transferId}", method = RequestMethod.GET)
    public Transfer getSpecifiedTransfer(Principal principal, @PathVariable int transactionId){  //using Principal for better security,and allowing transactionId to be variable
        int userId = userDao.findIdByUsername(principal.getName()); //getting our userId in a secure way
        return transferDao.getTransferById(userId, transactionId); //using our userId and transactionId
    }

    //As an authenticated user of the system, I need to be able to *send* a transfer of a specific amount of TE Bucks to a registered user.
    @RequestMapping(path = "account/transfer/send", method = RequestMethod.POST)
    public String sendBucks(Principal principal, @RequestBody Transfer transfer){

        //Transfer t = new Transfer();
        int userId = userDao.findIdByUsername(principal.getName()); //getting our userId in a secure way
        return transferDao.sendTransfer(userId, transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getAmount());

        // 415 : [{"timestamp":"2022-03-11T21:19:41.453+00:00","status":415,"error":"Unsupported Media Type",
        // "message":"Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported","path":"/account/transfer/send"}]
    }
















}
