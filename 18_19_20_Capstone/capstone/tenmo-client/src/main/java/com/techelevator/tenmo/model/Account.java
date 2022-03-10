package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class Account {

    @NotBlank(message = "The field `title` should not be blank.")
    private int accountId;
    @NotBlank(message = "The field `title` should not be blank.")
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


    //methods

    @Override
    public String toString(){
        return String.valueOf(balance);
    }

}
