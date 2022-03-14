package com.techelevator.tenmo.services;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import com.techelevator.util.BasicLogger;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class UserService {


    private String BASE_URL;
    private RestTemplate restTemplate = new RestTemplate();

    public UserService (String url){
        BASE_URL = url;
    }


    //todo --> add in our endpoint
    public User[] getListOfUsers(String token) {
        User[] u = null;

        try {
            ResponseEntity<User[]> response = restTemplate.exchange(BASE_URL + "users", HttpMethod.GET, makeAuthEntity(token), User[].class);
            u = response.getBody();
            for (User s : u){
                System.out.println(s.getUsername() + ", ");
            }
        } catch (Exception e) {
            System.out.println("Sorry, there was an error.");
            BasicLogger.log("Error getting list of users: " + e.getMessage());
        }
        return u;
    }

    public Account getAccountByUsername(String token, String username){
        Account a = null;

        try {
            ResponseEntity<Account> response = restTemplate.exchange(BASE_URL + "users/getAccount", HttpMethod.GET, makeAuthEntity(token), Account.class);
            a = response.getBody();
        } catch (Exception e) {
            System.out.println("Sorry, there was an error.");
            BasicLogger.log("Error getting account by username: " + e.getMessage());
        }
        return a;
    }


    private HttpEntity makeAuthEntity(String token) { //authenticating our entity
        HttpHeaders headers = new HttpHeaders(); //make sure to import the correct package! only one of the auto-fill options works.
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        return new HttpEntity<>(headers); //returning our authenticated object

        //find accountIdFromUsername
    }

}
