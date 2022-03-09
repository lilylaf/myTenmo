package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;


public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //override method for Account getUserBalance()
    @Override
    public Account getUserBalance(int userId){
        Account a = null;

        String sql = "SELECT balance " +
                     "FROM account " +
                     "WHERE user_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        if (results.next()){
            a = mapRowToAccount(results);
        }
        return a;
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

