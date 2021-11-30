package application;

import database.*;

public class DatabaseAPI {
    private static BrokerAccommodationsDB brokerAccommodationsDatabase;
    private static UserConfirmationsDB userConfirmationsDatabase;

    private static CredentialsUserDB credentialsUserDatabase;

    private static UserMessagesDB userMessagesDatabase;
    private static ReviewsDB reviewsDatabase;
    private static ReservationsDB reservationDatabase;


    public DatabaseAPI() {
        reservationDatabase = new ReservationsDB();
        brokerAccommodationsDatabase = new BrokerAccommodationsDB();
        credentialsUserDatabase = new CredentialsUserDB();
        reviewsDatabase = new ReviewsDB();
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

    public static ReservationsDB getReservationsDatabase() {
        return reservationDatabase;
    }

    public static UserMessagesDB getUserMessagesDatabase() {
        return userMessagesDatabase;
    }

    public static ReviewsDB getReviewsDatabase() {
        return reviewsDatabase;
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
