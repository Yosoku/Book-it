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

/**
 * A class representing the Server Side in the Booking Application.The server is responsible for receiving the requests
 * and pointing them to the appropriate Handler for each request. It provides information about the current user and a method
 * for establishing a connection
 *
 * @author Edward Koulakidis
 */
public class Server {
    public boolean quit;
    private User currentUser = null;

    /**
     * Method for establishing a connection to the Server
     * @param request The request to handle,can be either 'signin','signup' or 'signout'
     * @return true if a connection has been established,false otherwise
     */
    public boolean handleConnectionRequests(String request) {
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
        return false;
    }


    /**
     * The method responsible for pointing requests to their appropriate Handler e.g. Customer Handler,Broker Handler etc
     */
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

    /**
     * Method for getting information about the user currently making requests. If the return value is null it means that
     * either a connection failed to establish,or the request to establish a connection has not been made yet
     *
     * @return The current user talking to the Server
     */
    public User getCurrentUser() {
        return currentUser;
    }


}
