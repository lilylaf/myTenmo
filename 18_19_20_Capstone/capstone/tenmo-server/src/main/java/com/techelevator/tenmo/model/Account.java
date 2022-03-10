package com.techelevator.tenmo.model;


import java.math.BigDecimal;

public class Account {

    //variables

    private int accountId;
    private int userId;
    private BigDecimal balance;


    //getters & setters

    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getUserId() { return userId; }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    //constructor

    public Account(){
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
    }


    //methods
    @Override
    public String toString(){
        return String.valueOf(balance);
    }

}
