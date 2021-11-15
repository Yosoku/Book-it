package database;

import communication.Review;
import users.Customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerReviews extends Database {
    private final HashMap<Customer, ArrayList<Review>> userReviews;

    public CustomerReviews() {
        super(DatabaseType.CUSTOMER_REVIEWS, "");
        userReviews = new HashMap<Customer, ArrayList<Review>>();
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

    @Override
    public void write() {

    }

    @Override
    public void read() {

    }
}
