package com.techelevator.tenmo.model;

import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class Account {

    @NotBlank(message = "The field `title` should not be blank.")
    private int accountId;
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @NotBlank(message = "The field `title` should not be blank.")
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


    //methods
    @Override
    public String toString(){
        return String.valueOf(balance);
    }

}
