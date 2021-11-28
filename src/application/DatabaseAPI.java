package application;

import database.*;

public class DatabaseAPI {
    private static BrokerAccommodations brokerAccommodationsDatabase;
    private static UserConfirmations userConfirmationsDatabase;
    private static AccommodationReviews accommodationReviewsDatabase;
    private static CredentialsUser credentialsUserDatabase;
    private static CustomerReviews customerReviewsDatabase;
    private static UserMessages userMessagesDatabase;


    public DatabaseAPI() {
        accommodationReviewsDatabase = new AccommodationReviews();
        brokerAccommodationsDatabase = new BrokerAccommodations();
        credentialsUserDatabase = new CredentialsUser();
        customerReviewsDatabase = new CustomerReviews();
        userConfirmationsDatabase = new UserConfirmations();
        userMessagesDatabase = new UserMessages();
    }


    public static BrokerAccommodations getBrokerAccommodationsDatabase() {
        return brokerAccommodationsDatabase;
    }

    public static UserConfirmations getUserConfirmationsDatabase() {
        return userConfirmationsDatabase;
    }

    public static AccommodationReviews getAccommodationReviewsDatabase() {
        return accommodationReviewsDatabase;
    }

    public static CredentialsUser getCredentialsUserDatabase() {
        return credentialsUserDatabase;
    }

    public static CustomerReviews getCustomerReviewsDatabase() {
        return customerReviewsDatabase;
    }

    public static UserMessages getUserMessagesDatabase() {
        return userMessagesDatabase;
    }


    private void loadData() {
        accommodationReviewsDatabase = (AccommodationReviews) accommodationReviewsDatabase.read();
        brokerAccommodationsDatabase = (BrokerAccommodations) brokerAccommodationsDatabase.read();
        credentialsUserDatabase = (CredentialsUser) credentialsUserDatabase.read();
        customerReviewsDatabase = (CustomerReviews) customerReviewsDatabase.read();
        userConfirmationsDatabase = (UserConfirmations) userConfirmationsDatabase.read();
        userMessagesDatabase = (UserMessages) userMessagesDatabase.read();
    }


    private void writeData() {
        accommodationReviewsDatabase.write();
        brokerAccommodationsDatabase.write();
        credentialsUserDatabase.write();
        customerReviewsDatabase.write();
        userConfirmationsDatabase.write();
        userMessagesDatabase.write();
    }
}
