package application;

import UI.UI;
import UI.UIMessage;
import database.*;

public class DatabaseAPI {
    public static BrokerAccommodationsDB brokerAccommodationsDatabase = new BrokerAccommodationsDB();
    public static CredentialsUserDB credentialsUserDatabase = new CredentialsUserDB();
    public static ReservationsDB reservationDatabase = new ReservationsDB();
    public static ReviewsDB reviewsDatabase = new ReviewsDB();
    public static UserConfirmationsDB userConfirmationsDatabase = new UserConfirmationsDB();
    public static UserMessagesDB userMessagesDatabase = new UserMessagesDB();
    public static AccommodationsCalendarDB accommodationsCalendarDatabase = new AccommodationsCalendarDB();


    public static void loadData() {
        UI.LOG(UIMessage.LOADING);
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
