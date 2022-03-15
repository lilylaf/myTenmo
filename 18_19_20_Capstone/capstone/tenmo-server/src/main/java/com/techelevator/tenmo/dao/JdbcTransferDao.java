package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcTransferDao implements TransferDao{
    @Autowired  //googled this --said it provides "more fine grained control" code was not running, I tried adding this
                // and we got it running--not sure it is doing anything!
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private UserDao userDao;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate, AccountDao accountDao, UserDao userDao){
        this.jdbcTemplate = jdbcTemplate;
        this.accountDao = accountDao;
        this.userDao = userDao;
    }

    @Override
    public List<Transfer> getListOfTransfers(int userId){
        List<Transfer> list = new ArrayList<>();
        String sql = "SELECT t.*, c.username AS userFrom, d.username AS userTo\n" +
                     "FROM transfer t\n" +
                     "JOIN account a ON t.account_from = a.account_id\n" +
                     "JOIN account b ON t.account_to = b.account_id\n" +
                     "JOIN tenmo_user c ON a.user_id = c.user_id\n" +
                     "JOIN tenmo_user d ON b.user_id = d.user_id\n" +
                     "WHERE a.user_id = ? ;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId);
        while(results.next()){
            Transfer t = mapRowToTransfer(results);
            list.add(t);
        }
        return list;
    }

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

    @Override
    public String sendTransfer(int userId, int accountFrom, int accountTo, BigDecimal amount){
        if(accountFrom == accountTo){
            System.out.println("You cannot send money to yourself");
        }

        if(amount.compareTo(accountDao.findAccountById(userId).getBalance()) == -1){ //if (amount < userBalance)
            String sql = "INSERT INTO transfer (transfer_type_id, transfer_status_id, account_from, account_to, amount) " +
                         "Values (2, 2, ?, ?, ?);"; //the first value "2" =transfer type "send". the second value "2" = transfer status "approved".
            jdbcTemplate.update(sql, accountFrom, accountTo, amount);
            accountDao.addBalance(amount, accountTo); //calling method in accountDao
            accountDao.subtractBalance(amount, accountFrom); //calling method in accountDao
            System.out.println("Your transfer was completed.");
        } else {
            System.out.println("Your transfer was not completed.");
        }
        return null;
    }


    private Transfer mapRowToTransfer(SqlRowSet results){ //most likely will have SqlRowSet results in the parameter
        Transfer t = new Transfer();

        t.setTransferId(results.getInt("transfer_id"));
        t.setTransferTypeId(results.getInt("transfer_type_id"));
        t.setTransferStatusId(results.getInt("transfer_status_id"));
        t.setAmount(results.getBigDecimal("amount"));
        t.setAccountFrom(results.getInt("account_from"));
        t.setAccountTo(results.getInt("account_to"));

        return t;
    }
}
