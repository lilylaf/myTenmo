package com.techelevator.tenmo.dao;


import com.techelevator.tenmo.model.Transfer;
import org.springframework.stereotype.Component;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTransferDao jdbcTemplate;
    private AccountDao accountDao;

    //Methods that we need:


    //getListOfTransfers
    //method to get all transfers(list) --> probably taking in userId
        //you can see all transfers sent and received


    //getSpecifiedTransfer
    //method to get specified transfer --> probably taking in transactionId


    //sendTransfer
    //method to send a transfer --> probably taking in amount, account from, and account to
        //*** prevent user from sending money to themselves ***
        // should be able to select a user from a list of users
        // the receiver(account to) needs to have their account balance increase by amount of transfer
        // the sender(account from) needs to have their account balance decrease by amount of transfer
        // ** should not be able to send more money than you have in account **
        // ** should not be able to send a zero or negative amount
        // sending a transfer should have an initial status of *Approved*







    //mapRowToTransfer
    private Transfer mapRowToTransfer(){ //most likely will have SqlRowSet results in the parameter
        Transfer t = new Transfer();

        //we need to fill out the body

        return t;
    }

}
