package com.techelevator.tenmo.services;

import com.techelevator.util.BasicLogger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;


public class AccountService {

    //variables

    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();

    //constructor

    public AccountService(String url) {
        BASE_URL = url;
    }


    //methods

    public BigDecimal getUserBalance(String token){ //we need to get the token to validate the user
        BigDecimal balance = new BigDecimal(0); //creating a BigDecimal value to return

        try{
            ResponseEntity<BigDecimal> response = restTemplate.exchange(BASE_URL + "account/balance", HttpMethod.GET, makeAuthEntity(token), BigDecimal.class); //idk how to explain this in english
            balance = response.getBody(); //setting the body of response equal to our return object
        } catch (Exception e){
            System.out.println("Error in getUserBalance: " + e.getMessage());
            BasicLogger.log("Error getting balance: " + e.getMessage());
        }
        return balance;
    }

    private HttpEntity makeAuthEntity(String token){ //authenticating our entity
        HttpHeaders headers = new HttpHeaders(); //make sure to import the correct package! only one of the auto-fill options works.
        headers.setBearerAuth(token); //getting our token from user login
        return new HttpEntity<>(headers); //returning our authenticated object
    }

//I think this is all finished!!
}
