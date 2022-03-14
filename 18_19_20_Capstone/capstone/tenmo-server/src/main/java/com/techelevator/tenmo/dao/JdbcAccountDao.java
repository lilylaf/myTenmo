package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotBlank;
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
    public BigDecimal addBalance(BigDecimal amount, int accountId) { // userId is being sent in as AccountId
        Account a = findAccountByAccountId(accountId);
        BigDecimal newBalance = a.getBalance().add(amount);
        //a.addToBalance(amount);
        //BigDecimal newBalance = a.getBalance();

        String sql = "UPDATE account\n" +
                     "SET balance = ?\n" +
                     "WHERE account_id = ?;";

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
    public Account findAccountById(int userId) { //is being sent accountID from add/subtract methods
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
    public Account findAccountByAccountId(int accountId) { //is being sent accountID from add/subtract methods
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
        String sql = "SELECT * " +
                "FROM account " +
                "NATURAL JOIN tenmo_user " +
                "WHERE username = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, name);
        if(results.next()) {
            return mapRowToAccount(results);
        } else {
            System.out.println("error, username not found");
            return null;
        }
    }




    //create a mapRowToAccount
    private Account mapRowToAccount(SqlRowSet results){
        Account a = new Account();

        a.setUserId(results.getInt("user_id"));
        a.setAccountId(results.getInt("account_id"));
        a.setBalance(results.getBigDecimal("balance"));

        return a;
    }
}

