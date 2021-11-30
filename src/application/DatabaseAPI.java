package application;

import database.BrokerAccommodationsDB;
import database.CredentialsUserDB;
import database.UserConfirmationsDB;
import database.UserMessagesDB;

public class DatabaseAPI {
    private static BrokerAccommodationsDB brokerAccommodationsDatabase;
    private static UserConfirmationsDB userConfirmationsDatabase;

    private static CredentialsUserDB credentialsUserDatabase;

    private static UserMessagesDB userMessagesDatabase;


    public DatabaseAPI() {

        brokerAccommodationsDatabase = new BrokerAccommodationsDB();
        credentialsUserDatabase = new CredentialsUserDB();

        userConfirmationsDatabase = new UserConfirmationsDB();
        userMessagesDatabase = new UserMessagesDB();
    }


    public static BrokerAccommodationsDB getBrokerAccommodationsDatabase() {
        return brokerAccommodationsDatabase;
    }

    public static UserConfirmationsDB getUserConfirmationsDatabase() {
        return userConfirmationsDatabase;
    }


    public static CredentialsUserDB getCredentialsUserDatabase() {
        return credentialsUserDatabase;
    }


    public static UserMessagesDB getUserMessagesDatabase() {
        return userMessagesDatabase;
    }


    private void loadData() {

        brokerAccommodationsDatabase = (BrokerAccommodationsDB) brokerAccommodationsDatabase.read();
        credentialsUserDatabase = (CredentialsUserDB) credentialsUserDatabase.read();

        userConfirmationsDatabase = (UserConfirmationsDB) userConfirmationsDatabase.read();
        userMessagesDatabase = (UserMessagesDB) userMessagesDatabase.read();
    }


    private void writeData() {

        brokerAccommodationsDatabase.write();
        credentialsUserDatabase.write();

        userConfirmationsDatabase.write();
        userMessagesDatabase.write();
    }
}
