package database;

import accommodations.Accommodation;
import communication.Review;
import database.authentication.Credentials;
import database.authentication.Encryption;
import users.Broker;
import users.Customer;
import users.User;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class daata {
    private HashMap<String, User> users;
    private HashMap<Broker, ArrayList<Accommodation>> brokerProperties;
    private HashMap<Customer, ArrayList<Review>> userReviews; // DB holding reviews a user writes
    private HashMap<Accommodation, ArrayList<Review>> accommodationReviews; // DB holding reviews a user writes

    public daata() {
        users = new HashMap<String, User>();
        brokerProperties = new HashMap<Broker, ArrayList<Accommodation>>();
        userReviews = new HashMap<Customer, ArrayList<Review>>();
        accommodationReviews = new HashMap<Accommodation, ArrayList<Review>>();
    }

    //=============================USER QUERIES=========================================================================

    public User selectUser(String credentialsHash) {
        if (users.containsKey(credentialsHash))
            return users.get(credentialsHash);
        return null;
    }

    public void insertUser(Credentials credentials, User user) throws NoSuchAlgorithmException {
        if (user != null)
            users.put(Encryption.SHA_512(credentials.toString()), user);
    }

    //=============================ACCOMMODATION QUERIES================================================================

    public List<Accommodation> selectProperties(Broker broker) {
        if (brokerProperties.containsKey(broker))
            return brokerProperties.get(broker);
        return null;
    }

    public void insertProperty(Accommodation accommodation, Broker broker) {
        if (broker == null || accommodation == null)
            return;
        ArrayList<Accommodation> accommodationList = brokerProperties.get(broker);
        if (accommodationList == null)
            accommodationList = new ArrayList<>();
        accommodationList.add(accommodation);
        brokerProperties.put(broker, accommodationList);
    }

    public void dropProperty(Accommodation accommodation, Broker broker) {
        if (broker == null || accommodation == null)
            return;
        brokerProperties.get(broker).remove(accommodation);
    }

    public void insertReviewToUser(Review review, Customer customer) {
        if (customer == null || review == null)
            return;
        ArrayList<Review> reviewList = userReviews.get(customer);
        if (reviewList == null)
            reviewList = new ArrayList<>();
        reviewList.add(review);
        userReviews.put(customer, reviewList);
    }

    public List<Review> selectReviewsFromUser(Customer customer) {
        if (userReviews.containsKey(customer))
            return userReviews.get(customer);
        return null;
    }

    public void dropReviewFromUser(Review review, Customer customer) {
        if (customer == null || review == null)
            return;
        userReviews.get(customer).remove(review);
    }

    public void insertReviewToAccommodation(Review review, Accommodation accommodation) {
        if (accommodation == null || review == null)
            return;
        ArrayList<Review> reviewList = accommodationReviews.get(accommodation);
        if (reviewList == null)
            reviewList = new ArrayList<>();
        reviewList.add(review);
        accommodationReviews.put(accommodation, reviewList);
    }

    public List<Review> selectReviewsFromAccommodation(Accommodation accommodation) {
        if (accommodationReviews.containsKey(accommodation))
            return accommodationReviews.get(accommodation);
        return null;
    }

    public void dropReviewFromAccommodation(Review review, Accommodation accommodation) {
        if (accommodation == null || review == null)
            return;
        accommodationReviews.get(accommodation).remove(review);
    }
}