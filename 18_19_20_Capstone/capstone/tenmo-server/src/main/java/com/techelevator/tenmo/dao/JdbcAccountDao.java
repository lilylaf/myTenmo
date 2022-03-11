package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import org.springframework.dao.DataAccessException;
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
       // BigDecimal balance = new BigDecimal("0.0");

        String sql = "SELECT balance\n" +
                     "FROM account\n" +
                     "WHERE user_id = ?;";
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, userId);
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
//        if(results.next()) {
//            balance = results.getBigDecimal("balance");
//        }
//        return balance;
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

