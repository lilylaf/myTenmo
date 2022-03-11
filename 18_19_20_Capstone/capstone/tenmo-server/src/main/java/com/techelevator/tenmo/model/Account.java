package com.techelevator.tenmo.model;


import java.math.BigDecimal;

public class Account {

    //variables

    private int accountId;

    private int userId;

    //add @Positive
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


    //constructors

    public Account(int accountId, int userId, BigDecimal balance){
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
    }

    public Account(){

    }

    //methods
    @Override
    public String toString(){
        return String.valueOf(balance);
    }

    public void addToBalance(BigDecimal a){
       balance = this.balance.add(a);
    }

    public void subtractFromBalance(BigDecimal s){
       balance = this.balance.subtract(s);
    }
}
