package backend.application;

import backend.database.*;

/**
 * An class representing an API to the Booking Clone Database. Provides 2 methods for reading and writing the whole Database
 * to files
 *
 * @author Edward Koulakidis
 */
public class DatabaseAPI {
    public static BrokerAccommodationsDB brokerAccommodationsDatabase = new BrokerAccommodationsDB();
    public static CredentialsUserDB credentialsUserDatabase = new CredentialsUserDB();
    public static ReservationsDB reservationDatabase = new ReservationsDB();
    public static ReviewsDB reviewsDatabase = new ReviewsDB();
    public static UserConfirmationsDB userConfirmationsDatabase = new UserConfirmationsDB();
    public static UserMessagesDB userMessagesDatabase = new UserMessagesDB();
    public static AccommodationsCalendarDB accommodationsCalendarDatabase = new AccommodationsCalendarDB();


    /**
     * Loads every Database in the member fields
     */
    public static void loadData() {
        Object temp = credentialsUserDatabase.read();
        if (temp != null)
            credentialsUserDatabase = (CredentialsUserDB) temp;
        temp = userConfirmationsDatabase.read();
        if (temp != null)
            userConfirmationsDatabase = (UserConfirmationsDB) temp;
        temp = accommodationsCalendarDatabase.read();
        if (temp != null)
            accommodationsCalendarDatabase = (AccommodationsCalendarDB) temp;
        temp = brokerAccommodationsDatabase.read();
        if (temp != null)
            brokerAccommodationsDatabase = (BrokerAccommodationsDB) temp;
        temp = reservationDatabase.read();
        if (temp != null)
            reservationDatabase = (ReservationsDB) temp;
        temp = reviewsDatabase.read();
        if (temp != null)
            reviewsDatabase = (ReviewsDB) temp;
        temp = userMessagesDatabase.read();
        if (temp != null)
            userMessagesDatabase = (UserMessagesDB) temp;
    }


    /**
     * Writes every Database to their respective files
     */
    public static void writeData() {
        accommodationsCalendarDatabase.write();
        brokerAccommodationsDatabase.write();
        credentialsUserDatabase.write();
        reservationDatabase.write();
        reviewsDatabase.write();
        userConfirmationsDatabase.write();
        userMessagesDatabase.write();
    }
}
