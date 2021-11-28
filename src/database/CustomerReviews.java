package database;

import communication.Review;
import users.Customer;

import java.io.Serial;
import java.util.HashMap;
import java.util.HashSet;

public class CustomerReviews extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private final HashMap<Customer, HashSet<Review>> userReviews;

    public CustomerReviews() {
        super("src/config/customerReviews.ser");
        userReviews = new HashMap<Customer, HashSet<Review>>();
    }


    public void insertReviewToUser(Customer customer, Review review) {
        if (customer == null || review == null)
            return;
        HashSet<Review> reviewList = userReviews.get(customer);
        if (reviewList == null)
            reviewList = new HashSet<>();
        reviewList.add(review);
        userReviews.put(customer, reviewList);
    }

    public HashSet<Review> selectReviewFromUser(Customer customer) {
            return userReviews.get(customer);
    }

    public void dropReviewFromUser(Review review, Customer customer) {
        if (customer == null || review == null)
            return;
        userReviews.get(customer).remove(review);
    }


}
