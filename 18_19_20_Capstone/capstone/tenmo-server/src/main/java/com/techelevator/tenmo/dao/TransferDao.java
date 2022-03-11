package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {


    //Methods for our Transfer

    //method to get all transfers(list)
    List<Transfer> getListOfTransfers(int userId);

    //method to get specified transfer
    Transfer getTransferById(int userId, int transactionId);

    //method to send a transfer
    Transfer sendTransfer(int userId, int accountTo, int accountFrom, BigDecimal amount);


}
