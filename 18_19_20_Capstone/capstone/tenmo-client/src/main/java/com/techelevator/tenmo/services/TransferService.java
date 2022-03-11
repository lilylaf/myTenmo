package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;



public class TransferService {

    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();
    private AuthenticatedUser currentUser;


    public TransferService (String url){
        BASE_URL = url;
    }

    public Transfer[] getListOfTransfers(){
        Transfer[] t = null;

        try {
            ResponseEntity<Transfer[]> response = restTemplate.exchange(BASE_URL + "account/transfer" + currentUser.getUser().getId(), HttpMethod.GET, authEntity(), Transfer[].class);
            t = response.getBody();
        } catch (Exception e) {
            System.out.println("There was an error: " + e.getMessage());
        }
        return t;
    }




    private HttpEntity authEntity(){
        HttpHeaders headers = new HttpHeaders(); //make sure to import the correct package! only one of the auto-fill options works.
        headers.setBearerAuth(currentUser.getToken()); //getting our token from user login
        return new HttpEntity<>(headers); //returning our authenticated object
    }
}
