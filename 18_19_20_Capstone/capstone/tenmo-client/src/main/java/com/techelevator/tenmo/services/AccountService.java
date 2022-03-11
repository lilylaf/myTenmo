package com.techelevator.tenmo.services;

import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class AccountService {

    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    //private AuthenticatedUser currentUser;


    public AccountService(String url) {
        BASE_URL = url;
    }

    public BigDecimal getUserBalance(String token){
        BigDecimal balance = new BigDecimal(0);

        try{
            ResponseEntity<BigDecimal> response = restTemplate.exchange(BASE_URL + "account/balance", HttpMethod.GET, makeAuthEntity(token), BigDecimal.class);
            balance = response.getBody();
        } catch (Exception e){
            System.out.println("Error in getUserBalance: " + e.getMessage());
            BasicLogger.log("Error getting balance: " + e.getMessage());
        }
        return balance;
    }

    private HttpEntity makeAuthEntity(String token){
        HttpHeaders headers = new HttpHeaders(); //make sure to import the correct package! only one of the auto-fill options works.
        headers.setBearerAuth(token); //getting our token from user login
        return new HttpEntity<>(headers); //returning our authenticated object
    }

//I think this is all finished!!
}
