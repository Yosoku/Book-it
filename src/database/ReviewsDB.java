package database;


import accommodations.Accommodation;
import communication.Review;
import users.User;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashSet;

public class ReviewsDB extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private HashSet<Review> reviews;


    public ReviewsDB() {
        super("src/config/reviews.ser");
        reviews = new HashSet<Review>();
    }

    public void insertReview(Review newReview) {
        if (newReview == null)
            return;
        reviews.add(newReview);
    }

    public ArrayList<Review> selectReviewsByUser(User user) {
        ArrayList<Review> temp = new ArrayList<Review>();
        if (user == null)
            return temp;
        for (Review review : reviews) {
            if (review.user().equals(user))
                temp.add(review);
        }
        return temp;
    }

    public ArrayList<Review> selectReviewsByAccommodation(Accommodation accommodation) {
        ArrayList<Review> temp = new ArrayList<Review>();
        if (accommodation == null)
            return temp;
        for (Review review : reviews) {
            if (review.accommodation().equals(accommodation))
                temp.add(review);
        }
        return temp;
    }

    public ArrayList<Review> selectReviewsByStars(int stars) {
        ArrayList<Review> temp = new ArrayList<Review>();
        if (stars > 5)
            return temp;
        for (Review review : reviews) {
            if (review.stars() >= stars)
                temp.add(review);
        }
        return temp;
    }

    public ArrayList<Review> selectReviewsInAccommodationByStars(int stars, Accommodation accommodation) {
        ArrayList<Review> temp = new ArrayList<Review>();
        if (accommodation == null || stars > 5)
            return temp;
        for (Review review : selectReviewsByAccommodation(accommodation)) {
            if (review.stars() >= stars)
                temp.add(review);
        }
        return temp;
    }
}
