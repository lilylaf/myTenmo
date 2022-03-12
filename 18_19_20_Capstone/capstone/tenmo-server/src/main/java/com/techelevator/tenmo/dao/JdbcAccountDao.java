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
    public BigDecimal addBalance(BigDecimal amount, int userId) {
        Account a = findAccountById(userId);
        BigDecimal newBalance = a.getBalance().add(amount);
        //a.addToBalance(amount);
        //BigDecimal newBalance = a.getBalance();

        String sql = "UPDATE account\n" +
                     "SET balance = ?\n" +
                     "WHERE account_id = ?;";

        jdbcTemplate.update(sql, newBalance, userId);
        return a.getBalance();
    }

    @Override
    public BigDecimal subtractBalance(BigDecimal amount, int userId) {
        Account a = findAccountById(userId);
        a.subtractFromBalance(amount);
        BigDecimal newBalance = a.getBalance();

        String sql = "UPDATE account\n" +
                     "SET balance = ?\n" +
                     "WHERE account_id = ?;";

        jdbcTemplate.update(sql, newBalance, userId);
        return a.getBalance();
    }

    @Override
    public Account findAccountById(int userId) {
        String sql = "SELECT * " +
                     "FROM account " +
                     "WHERE user_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if(results.next()) {
           return mapRowToAccount(results);
        } else {
            System.out.println("error, userID not found");
            return null;
        }
    }

    //getListOfUsers



    //create a mapRowToAccount
    private Account mapRowToAccount(SqlRowSet results){
        Account a = new Account();

        a.setUserId(results.getInt("user_id"));
        a.setAccountId(results.getInt("account_id"));
        a.setBalance(results.getBigDecimal("balance"));

        return a;
    }
}

