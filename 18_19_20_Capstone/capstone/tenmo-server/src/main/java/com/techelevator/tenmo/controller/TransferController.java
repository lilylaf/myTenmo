package com.techelevator.tenmo.controller;


import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")
public class TransferController {

    private TransferDao transferDao;
    private UserDao userDao;
    private AccountDao accountDao;

    public TransferController(TransferDao transferDao, UserDao userDao, AccountDao accountDao){
        this.transferDao = transferDao;
        this.userDao = userDao;
        this.accountDao = accountDao;
    }


    //As an authenticated user of the system, I need to be able to see transfers I have sent or received.
    @RequestMapping(path = "account/transfer", method = RequestMethod.GET)
    public List<Transfer> getAllTransfers(Principal principal){ //using Principal for better security
        int userId = userDao.findIdByUsername(principal.getName()); //utilizing our userDao to find our user
        return transferDao.getListOfTransfers(userId);
    }

    //As an authenticated user of the system, I need to be able to retrieve the details of any transfer based upon the transfer ID.
    @RequestMapping(path ="account/transfer/{transferId}", method = RequestMethod.GET)
    public Transfer getSpecifiedTransfer(Principal principal, @PathVariable int transactionId){  //using Principal for better security,and allowing transactionId to be variable
        int userId = userDao.findIdByUsername(principal.getName()); //getting our userId in a secure way
        return transferDao.getTransferById(userId, transactionId);
    }

    //As an authenticated user of the system, I need to be able to *send* a transfer of a specific amount of TE Bucks to a registered user.
    @RequestMapping(path = "account/transfer/send", method = RequestMethod.POST)
    public String sendBucks(Principal principal, @RequestBody Transfer transfer){
        int userId = userDao.findIdByUsername(principal.getName()); //getting our userId in a secure way
        return transferDao.sendTransfer(userId, accountDao.findAccountById(userDao.findIdByUsername(principal.getName())).getAccountId(),
                transfer.getAccountTo(), transfer.getAmount());
    }
    //our sendBucks can be slow, minimize the number of times we are hitting our database.
}
