package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;
    private TransferDao transferDao;
    private AccountDao accountDao;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    //method to get all transfers(list) --> probably taking in userId
    @Override
    public List<Transfer> getListOfTransfers(int userId){
        //todo should I keep userId here, or is there a different way to ensure we are accessing the correct user
        List<Transfer> list = new ArrayList<>();
        String sql = "SELECT transfer_id, transfer_type_id, account_from, account_to, amount\n" +    //amount & balance are decimal(13,2)
                     "FROM transfer\n" +
                     "JOIN account ON transfer.account_from = account.account_id\n" +
                     "JOIN tenmo_user ON tenmo_user.user_id = account.user_id\n" +
                     "WHERE tenmo_user.user_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            Transfer t = mapRowToTransfer(results);
            list.add(t);
        }
        return list;
    }

    //method to get specified transfer --> probably taking in transactionId
    @Override
    public Transfer getTransferById(int userId, int transactionId){
        Transfer transfer = null;
        String sql = "SELECT transfer_id, account_from, account_to, transfer_type_id, transfer_status_id, amount\n" +
                "FROM transfer\n" +
                "JOIN account ON transfer.account_from = account_id\n" +
                "JOIN tenmo_user ON tenmo_user.user_id = account.user_id\n" +
                "WHERE tenmo_user.user_id = ? AND transfer_id = ?; ";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, transactionId);
        if (results.next()){
            transfer = mapRowToTransfer(results);
        }
        return null;
    }


    //method to send a transfer --> probably taking in amount, account from, and account to
    @Override
    public Transfer sendTransfer(int userId, int accountTo, int accountFrom, BigDecimal amount){
        //*** prevent user from sending money to themselves ***
        // should be able to select a user from a list of users
        // the receiver(account to) needs to have their account balance increase by amount of transfer
        // the sender(account from) needs to have their account balance decrease by amount of transfer
        // ** should not be able to send more money than you have in account **
        // ** should not be able to send a zero or negative amount
        // sending a transfer should have an initial status of *Approved*
        return null;
    }

//    @Override
//    public Transfer makeTransfer(int userId) {  //--Not sure about this part
//        Transfer from = null;
//        String sql = "SELECT account_from, account_to, transfer_type_id, amount, balance\n" +    //amount & balance are decimal(13,2)
//                     "FROM transfer\n" +
//                     "JOIN account ON transfer.account_from = account_id\n" +
//                     "JOIN transfer_type ON transfer_type.transfer_type_id = transfer.transfer_type_id\n" +
//                     "JOIN tenmo_user ON tenmo_user.user_id = account.user_id\n" +
//                     "WHERE account_from=?, account_to=?, amount=?; ";
//
//        SqlRowSet results = jdbcTemplate.queryForRowSet()
//
//    }

//    SELECT account_from, account_to, transfer.transfer_type_id, amount, balance    --amount & balance are decimal(13,2)
//    FROM transfer
//    JOIN account ON transfer.account_from = account_id
//    JOIN transfer_type ON transfer_type.transfer_type_id = transfer.transfer_type_id
//    JOIN tenmo_user ON tenmo_user.user_id = account.user_id
//    WHERE user_id = ?


    //mapRowToTransfer
    private Transfer mapRowToTransfer(SqlRowSet results){ //most likely will have SqlRowSet results in the parameter
        Transfer t = new Transfer();

        t.setAccountFrom(results.getInt("account_from"));
        t.setTransferId(results.getInt("transfer_id"));
        t.setTransferTypeId(results.getInt("transfer_type_id"));
        t.setTransferStatusId(results.getInt("transfer_status_id"));
        t.setAccountTo(results.getInt("account_to"));
        t.setAmount(results.getBigDecimal("amount"));

        return t;

    }

}
