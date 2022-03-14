package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class UserService {


    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();


    //todo --> add in our endpoint
    public User[] getListOfUsers(String token) {
        User[] u = null;

        try {
            ResponseEntity<User[]> response = restTemplate.exchange(BASE_URL + "MAKE THIS ENDPOINT", HttpMethod.GET, makeAuthEntity(token), User[].class);
            u = response.getBody();
        } catch (Exception e) {
            System.out.println("There was an error in getListOfUsers: " + e.getMessage());
            BasicLogger.log("Error getting transfer history: " + e.getMessage());
        }
        return u;
    }

    //getAccountIdFromUsername


    private HttpEntity makeAuthEntity(String token) { //authenticating our entity
        HttpHeaders headers = new HttpHeaders(); //make sure to import the correct package! only one of the auto-fill options works.
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token); //getting our token from user login
        return new HttpEntity<>(headers); //returning our authenticated object

        //find accountIdFromUsername
    }
}
