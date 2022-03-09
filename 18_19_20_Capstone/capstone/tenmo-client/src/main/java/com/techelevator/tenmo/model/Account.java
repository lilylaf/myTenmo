package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {

    private int accountId;
  //  @Min(value = 1, message = "The field 'accountId' is required.")
    public int getAccountId() { return accountId; }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    private int userId;
    //  @Min(value = 1, message = "The field 'userId' is required.")
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
