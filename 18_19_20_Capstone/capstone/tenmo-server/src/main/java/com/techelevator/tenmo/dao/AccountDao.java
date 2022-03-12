package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    BigDecimal getUserBalance(int userId);
    BigDecimal addBalance(BigDecimal amount, int userId);
    BigDecimal subtractBalance(BigDecimal amount, int userId);
    public Account findAccountById(int userId);
    public Account findAccountByAccountId(int accountId);
}
