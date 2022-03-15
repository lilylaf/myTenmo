package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;

import java.math.BigDecimal;
import java.util.List;

public interface TransferDao {

    List<Transfer> getListOfTransfers(int userId);
    Transfer getTransferById(int userId, int transactionId);
    String sendTransfer(int userId, int accountTo, int accountFrom, BigDecimal amount);
}
