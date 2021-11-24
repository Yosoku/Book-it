package database;

import accommodations.Accommodation;
import communication.Review;

import java.io.Serial;
import java.util.HashMap;
import java.util.HashSet;

public class AccommodationReviews extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private final HashMap<Accommodation, HashSet<Review>> accommodationReviews; // DB holding reviews a user writes

    public AccommodationReviews() {
        super("src/config/accommodationReviews.ser");
        accommodationReviews = new HashMap<Accommodation, HashSet<Review>>();
    }


    public void insertReviewToAccommodation(Accommodation accommodation, Review review) {
        if (accommodation == null || review == null)
            return;
        HashSet<Review> reviewList = accommodationReviews.get(accommodation);
        if (reviewList == null)
            reviewList = new HashSet<>();
        reviewList.add(review);
        accommodationReviews.put(accommodation, reviewList);
    }

    public HashSet<Review> selectReviewFromAccommodation(Accommodation accommodation) {
        if (accommodationReviews.containsKey(accommodation))
            return accommodationReviews.get(accommodation);
        return null;
    }

    public void dropReviewFromAccommodation(Review review, Accommodation accommodation) {
        if (accommodation == null || review == null)
            return;
        accommodationReviews.get(accommodation).remove(review);
    }


    public void dropAllReviewsFromAccommodation(Accommodation accommodation) {
        if (accommodation == null)
            return;
        accommodationReviews.get(accommodation).clear();
    }
}
