package database;

import communication.Review;
import users.Customer;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerReviews extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private final HashMap<Customer, ArrayList<Review>> userReviews;

    public CustomerReviews() {
        super("src/config/customerReviews.ser");
        userReviews = new HashMap<Customer, ArrayList<Review>>();
    }


    public void insertReviewToUser(Customer customer, Review review) {
        if (customer == null || review == null)
            return;
        ArrayList<Review> reviewList = userReviews.get(customer);
        if (reviewList == null)
            reviewList = new ArrayList<>();
        reviewList.add(review);
        userReviews.put(customer, reviewList);
    }

    public List<Review> selectReviewFromUser(Customer customer) {
        if (userReviews.containsKey(customer))
            return userReviews.get(customer);
        return null;
    }

    public void dropReviewFromUser(Review review, Customer customer) {
        if (customer == null || review == null)
            return;
        userReviews.get(customer).remove(review);
    }


}
