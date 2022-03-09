package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    //variables
    private int accountId;
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    private int userId;
    public int getUserId() { return userId; }
    public void setUserId(int userId) {
        this.userId = userId;
    }

    private BigDecimal balance;
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
