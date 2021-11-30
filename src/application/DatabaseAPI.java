package application;

import database.*;

public class DatabaseAPI {
    private static BrokerAccommodationsDB brokerAccommodationsDatabase;
    private static CredentialsUserDB credentialsUserDatabase;
    private static ReservationsDB reservationDatabase;
    private static ReviewsDB reviewsDatabase;
    private static UserConfirmationsDB userConfirmationsDatabase;
    private static UserMessagesDB userMessagesDatabase;


    public DatabaseAPI() {
        brokerAccommodationsDatabase = new BrokerAccommodationsDB();
        credentialsUserDatabase = new CredentialsUserDB();
        reservationDatabase = new ReservationsDB();
        reviewsDatabase = new ReviewsDB();
        userConfirmationsDatabase = new UserConfirmationsDB();
        userMessagesDatabase = new UserMessagesDB();
        loadData();
    }


    public static BrokerAccommodationsDB getBrokerAccommodationsDatabase() {
        return brokerAccommodationsDatabase;
    }


    public static CredentialsUserDB getCredentialsUserDatabase() {
        return credentialsUserDatabase;
    }

    public static ReservationsDB getReservationsDatabase() {
        return reservationDatabase;
    }

    public static ReviewsDB getReviewsDatabase() {
        return reviewsDatabase;
    }

    public static UserConfirmationsDB getUserConfirmationsDatabase() {
        return userConfirmationsDatabase;
    }

    public static UserMessagesDB getUserMessagesDatabase() {
        return userMessagesDatabase;
    }


    private void loadData() {

        brokerAccommodationsDatabase = (BrokerAccommodationsDB) brokerAccommodationsDatabase.read();
        credentialsUserDatabase = (CredentialsUserDB) credentialsUserDatabase.read();
        reviewsDatabase = (ReviewsDB) reviewsDatabase.read();
        reservationDatabase = (ReservationsDB) reservationDatabase.read();
        userConfirmationsDatabase = (UserConfirmationsDB) userConfirmationsDatabase.read();
        userMessagesDatabase = (UserMessagesDB) userMessagesDatabase.read();
    }


    public static void writeData() {
        brokerAccommodationsDatabase.write();
        credentialsUserDatabase.write();
        reservationDatabase.write();
        reviewsDatabase.write();
        userConfirmationsDatabase.write();
        userMessagesDatabase.write();
    }
}
