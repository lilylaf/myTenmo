package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public BigDecimal getUserBalance(int userId){

        String sql = "SELECT balance\n" +
                     "FROM account\n" +
                     "WHERE user_id = ?;";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
    }

    @Override
    public BigDecimal addBalance(BigDecimal amount, int accountId) {
        Account a = findAccountByAccountId(accountId);
        a.addToBalance(amount);
        BigDecimal newBalance = a.getBalance();

        String sql = "UPDATE account\n" +
                     "SET balance = ?\n" +
                     "WHERE account_id = ?;";

        //set balance = balance + ?;
        jdbcTemplate.update(sql, newBalance, accountId);
        return a.getBalance();
    }

    @Override
    public BigDecimal subtractBalance(BigDecimal amount, int accountId) {
        Account a = findAccountByAccountId(accountId);
        a.subtractFromBalance(amount);
        BigDecimal newBalance = a.getBalance();

        String sql = "UPDATE account\n" +
                     "SET balance = ?\n" +
                     "WHERE account_id = ?;";

        jdbcTemplate.update(sql, newBalance, accountId);
        return a.getBalance();
    }

    @Override
    public Account findAccountById(int userId) {
        String sql = "SELECT * " +
                "FROM account " +
                "WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()) {
            return mapRowToAccount(results);
        } else {
            System.out.println("error, userID not found");
            return null;
        }
    }

    @Override
    public Account findAccountByAccountId(int accountId) {
        String sql = "SELECT * " +
                "FROM account " +
                "WHERE account_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
        if(results.next()) {
            return mapRowToAccount(results);
        } else {
            System.out.println("error, accountID not found");
            return null;
        }
    }

    @Override
    public Account findAccountByName(String name){
        String sql = "SELECT account_id " +
                "FROM account " +
                "JOIN tenmo_user ON account.user_id = tenmo_user.user_id " +
                "WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        if(results.next()) {
            return mapRowToAccount(results);
        } else {
            System.out.println("error, username not found");
            return null;
        }
    }

    private Account mapRowToAccount(SqlRowSet results){
        Account a = new Account();

        a.setUserId(results.getInt("user_id"));
        a.setAccountId(results.getInt("account_id"));
        a.setBalance(results.getBigDecimal("balance"));

        return a;
    }
}

