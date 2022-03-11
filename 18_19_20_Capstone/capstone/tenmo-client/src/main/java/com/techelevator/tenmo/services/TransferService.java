package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class TransferService {

    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();

    public TransferService (String url){
        BASE_URL = url;
    }

    //get transfer history
    public Transfer[] getListOfTransfers(String token){
        Transfer[] t = null;

        try {
            ResponseEntity<Transfer[]> response = restTemplate.exchange(BASE_URL + "account/transfer" +  makeAuthEntity(token), HttpMethod.GET, makeAuthEntity(token), Transfer[].class);
            t = response.getBody();
        } catch (Exception e) {
            System.out.println("There was an error in getListOfTransfers: " + e.getMessage());
            BasicLogger.log("Error getting transfer history: " + e.getMessage());
        }
        return t;
    }

    //get specific transaction

    //send a transaction
    public void sendTransfer(String token, int accountId, BigDecimal amount){
        try {
            restTemplate.exchange(BASE_URL + "account/transfer/send", HttpMethod.POST, makeAuthEntity(token), Transfer.class);
        } catch (Exception e) {
            System.out.println("There was an error in sending your transfer: " + e.getMessage());
            BasicLogger.log("Error sending transfer: " + e.getMessage());
        }
    }



    private HttpEntity makeAuthEntity(String token){ //authenticating our entity
        HttpHeaders headers = new HttpHeaders(); //make sure to import the correct package! only one of the auto-fill options works.
        headers.setBearerAuth(token); //getting our token from user login
        return new HttpEntity<>(headers); //returning our authenticated object
    }
}
