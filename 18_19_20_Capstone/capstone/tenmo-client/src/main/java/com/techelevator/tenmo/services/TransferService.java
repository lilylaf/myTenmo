package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import org.springframework.web.client.RestTemplate;

public class TransferService {

    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;


    //we are setting our current user and base url for this AccountService
    public TransferService (String url, AuthenticatedUser currentUser){
        this.currentUser = currentUser;
        BASE_URL = url;
    }

    //code for our methods will probably go here


    //I think we might need an HttpEntity here? let's look at our security and/or client facing notes for how to do this

}
