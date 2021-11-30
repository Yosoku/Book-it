package database;


import accommodations.Accommodation;
import communication.Review;
import users.User;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * <p>
 * The ReviewsDB is a class intended to handle information about Reviews in an application. It is implemented by a
 * Hashset holding unique Reviews about Accommodations. A Review instance holds information about an Accommodation and
 * the user who wrote the review besides the stars and description</p>
 * <p>
 * The ReviewsDB inherits from Database inheriting read/write methods and implementing Serializable interface. It also
 * provides basic queries like SELECT,INSERT and DROP. It is also important to note that <b>null</b> values are not stored
 * in this class</p>
 *
 * @author Edward Koulakidis
 * @see java.io.Serializable
 * @see Database
 */

public class ReviewsDB extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private HashSet<Review> reviews;

    /**
     * An initializer constructor used to initialize the Hashset and call the Base class with the specified filename to store
     * the values of the Hashset
     */
    public ReviewsDB() {
        super("src/config/reviews.ser");
        reviews = new HashSet<Review>();
    }

    /**
     * A query for inserting new Reviews in the Database. If any of the parameters is <b>null</b> the method will exit
     * without changing the HashSet. Runs in O(1) time
     *
     * @param newReview The new Review to insert in the HashSet
     */
    public void insertReview(Review newReview) {
        if (newReview == null)
            return;
        reviews.add(newReview);
    }

    /**
     * A query for selecting all Reviews written by a specific User. Runs in O(r) time where r = Number of reviews
     *
     * @param user The user arguement used we need the Reviews from
     * @return An ArrayList of all the Reviews the User has wrote. If the User arguement is null the method returns
     * an empty ArrayList
     */
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

    /**
     * A query for selecting Reviews by a specific Accommodation. Runs in O(r) time where r = Number of reviews
     *
     * @param accommodation The Accommodation instance used to Query the HashSet
     * @return An ArrayList of all the Reviews written on accommodation. If the accommodation arguement is null the method
     * returns an empty ArrayList
     */
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

    /**
     * A query for selecting Reviews with stars greater than or equal to the arguement. If the arguement is 0,the method returns all
     * reviews in the HashSet. Runs in O(r) time where r = Number of reviews
     *
     * @param stars The minimum number of stars a Review needs to match to be returned
     * @return An ArrayList of all the Reviews with stars greater than or equal to the arguement. If stars is greater than
     * max stars(5) the method returns an empty ArrayList
     */
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

    /**
     * A query for selecting Reviews with stars greater than or equal to the arguement for a specific Accommodation.
     * If the arguement is 0,the method returns all reviews for an Accommodation. Runs in O(r) time where r = Number of reviews
     *
     * @param stars         The minimum number of stars a Review needs to match to be returned
     * @param accommodation The accommodation we query the reviews from
     * @return An ArrayList of all the Reviews with stars greater than or equal to the arguement for a specific accommodation
     * If stars is greater than max stars(5) the method returns an empty ArrayList
     */
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
