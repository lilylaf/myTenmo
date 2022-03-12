package com.techelevator.tenmo;

import com.techelevator.tenmo.model.*;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;

import java.math.BigDecimal;
import java.util.Arrays;

public class App {

    private static final String API_BASE_URL = "http://localhost:8080/";

    private AuthenticatedUser currentUser;

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    private AccountService accountService = new AccountService(API_BASE_URL);
    private TransferService transferService = new TransferService(API_BASE_URL);



    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();
        if (authenticationService.register(credentials)) {
            System.out.println("Registration successful. You can now login.");
        } else {
            consoleService.printErrorMessage();
        }
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();
        currentUser = authenticationService.login(credentials);
        if (currentUser == null) {
            consoleService.printErrorMessage();
        }
    }

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }
            consoleService.pause();
        }
    }

	private void viewCurrentBalance() {
        System.out.println("Your balance is: " + accountService.getUserBalance(currentUser.getToken()));
	}

	private void viewTransferHistory() {
        System.out.println("The following is your transfer History: " + Arrays.toString(transferService.getListOfTransfers(currentUser.getToken())));
	}

	private void viewPendingRequests() { //create instance of TransferService, not Transfer
		// TODO Auto-generated method stub
		
	}

	private void sendBucks() { //create instance of TransferService, not Transfer
		// TODO Auto-generated method stub
        //TODO --> generate a list of all users

        try{
            int userSendingBucksTo = consoleService.promptForInt("Enter the Account ID of the user you would like to send bucks to: ");
            BigDecimal amount = consoleService.promptForBigDecimal("Please enter the amount you would like to transfer: ");
            Transfer transfer = new Transfer(); //create transfer object here.  transfer.setUserSendBucksTo & amount.  then replace line 116 wi
            transfer.setAccountTo(userSendingBucksTo);
            transfer.setAmount(amount);
            transferService.sendTransfer(currentUser.getToken(), transfer);
        } catch (Exception e){
            System.out.println("error was in the app: " + e.getMessage());
        }
	}

	private void requestBucks() { //create instance of TransferService, not Transfer
		// TODO Auto-generated method stub
		
	}

}
