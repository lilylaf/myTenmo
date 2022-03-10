package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.web.client.RestTemplate;

public class AccountService {

    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;

    //we are setting our current user and base url for this AccountService
    public AccountService(String url, AuthenticatedUser currentUser){
        this.currentUser = currentUser;
        BASE_URL = url;
    }

    //BigDecimal getUserBalance method
        //I think we need a try catch to catch for errors possibly


    //I think we might need an HttpEntity here? let's look at our security and/or client facing notes for how to do this


}
