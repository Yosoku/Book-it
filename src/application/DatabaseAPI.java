package application;

import database.*;

public class DatabaseAPI {
    private static BrokerAccommodationsDB brokerAccommodationsDatabase = new BrokerAccommodationsDB();
    private static CredentialsUserDB credentialsUserDatabase = new CredentialsUserDB();
    private static ReservationsDB reservationDatabase = new ReservationsDB();
    private static ReviewsDB reviewsDatabase = new ReviewsDB();
    private static UserConfirmationsDB userConfirmationsDatabase = new UserConfirmationsDB();
    private static UserMessagesDB userMessagesDatabase = new UserMessagesDB();


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


    public static void loadData() {
        Object temp = brokerAccommodationsDatabase.read();
        if (temp != null)
            brokerAccommodationsDatabase = (BrokerAccommodationsDB) temp;
        temp = credentialsUserDatabase.read();
        if (temp != null)
            credentialsUserDatabase = (CredentialsUserDB) temp;
        temp = reservationDatabase.read();
        if (temp != null)
            reservationDatabase = (ReservationsDB) temp;
        temp = reviewsDatabase.read();
        if (temp != null)
            reviewsDatabase = (ReviewsDB) temp;
        temp = userConfirmationsDatabase.read();
        if (temp != null)
            userConfirmationsDatabase = (UserConfirmationsDB) temp;
        temp = userMessagesDatabase.read();
        if (temp != null)
            userMessagesDatabase = (UserMessagesDB) temp;
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
