package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {

    //variables

    private int transferId;
    private int transferTypeId;
    private int transferStatusId;
    private int accountFrom;
    private int accountTo;
    private BigDecimal amount;


    //getters & setters

    public int getTransferId(){
        return transferId;
    }
    public void setTransferId(int transferId){
        this.transferId = transferId;
    }

    public int getTransferTypeId(){
        return transferTypeId;
    }
    public void setTransferTypeId(int transferTypeId) {
        this.transferTypeId = transferTypeId;
    }

    public int getTransferStatusId() {
        return transferStatusId;
    }
    public void setTransferStatusId(int transferStatusId) {
        this.transferStatusId = transferStatusId;
    }

    public int getAccountFrom() {
        return accountFrom;
    }
    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }
    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }


    //constructor

    public Transfer(){
        //I don't think we need anything in here
    }

    //methods

    @Override
    public String toString(){
        String s = "Transfer ID: " + transferId +
                   "Transfer Type ID: " + transferTypeId +
                   "Transfer Status ID: " + transferStatusId +
                   "Account From: " + accountFrom +
                   "Account To: " + accountTo +
                   "Amount: " + amount;
        return s;
    }

}
