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
    private Handler handler;

    public boolean handleConnectionRequests(String request) throws NoSuchAlgorithmException {
        switch (request) {
            case "signin" -> {
                SignInUI signin = new SignInUI();
                signin.show();
                String[] request1 = signin.getRequest().split("\\s");
                Credentials credentials = new Credentials(request1[0], request1[1]);
                currentUser = DatabaseAPI.getCredentialsUserDatabase().selectUser(credentials);
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
                if (DatabaseAPI.getCredentialsUserDatabase().selectUser(currentUser.getCredentials()) != null) { // duplicate credentials
                    UI.LOG(UIMessage.SIGN_UP_FAILED);
                    return false;
                } else {
                    UI.LOG(UIMessage.SIGN_UP_SUCCESS);
                    DatabaseAPI.getCredentialsUserDatabase().insertUser(currentUser.getCredentials(), currentUser);
                    DatabaseAPI.getUserConfirmationsDatabase().insertUserConfirmation(currentUser);
                    return true;
                }
            }
            case "quit" -> quit = true;
        }
        System.out.println("wtf");
        return false;
    }


    public void handleUserRequests() {
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
