package application;

import UI.SignInUI;
import UI.SignUpUI;
import UI.UI;
import UI.UIMessage;
import auth.Credentials;
import requests.AdminRequests;
import requests.BrokerRequests;
import requests.CustomerRequests;
import requests.Handler;
import users.Admin;
import users.Broker;
import users.Customer;
import users.User;

import java.security.NoSuchAlgorithmException;

public class Server {
    private User currentUser = null;
    public boolean quit;

    public boolean handleConnectionRequests(String request) throws NoSuchAlgorithmException {
        switch (request) {
            case "signin" -> {
                SignInUI signin = new SignInUI();
                signin.show();
                String[] request1 = signin.getRequest().split("\\s");
                Credentials credentials = new Credentials(request1[0], request1[1]);
                currentUser = DatabaseAPI.credentialsUserDatabase.selectUser(credentials);
                if (currentUser == null) {
                    UI.LOG(UIMessage.SIGN_IN_FAILED);
                    return false;
                } else {
                    UI.LOG(UIMessage.SIGN_IN_SUCCESS);
                    return true;
                }
            }
            case "signup" -> {
                SignUpUI signup = new SignUpUI();
                signup.show();
                currentUser = signup.getNewUser();
                if (DatabaseAPI.credentialsUserDatabase.selectUser(currentUser.getCredentials()) != null) { // duplicate credentials
                    UI.LOG(UIMessage.SIGN_UP_FAILED);
                    return false;
                } else {
                    UI.LOG(UIMessage.SIGN_UP_SUCCESS);
                    DatabaseAPI.credentialsUserDatabase.insertUser(currentUser.getCredentials(), currentUser);
                    DatabaseAPI.userConfirmationsDatabase.insertUserConfirmation(currentUser);
                    return true;
                }
            }
        }
        //request = quit
        return false;
    }


    public void handleUserRequests() {
        Handler handler;
        switch (currentUser.getPrivilege()) {
            case CUSTOMER -> {
                handler = new CustomerRequests((Customer) currentUser);
                handler.handleRequests();
            }
            case BROKER -> {
                handler = new BrokerRequests((Broker) currentUser);
                handler.handleRequests();
            }
            case ADMIN -> {
                handler = new AdminRequests((Admin) currentUser);
                handler.handleRequests();
            }
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }


}
