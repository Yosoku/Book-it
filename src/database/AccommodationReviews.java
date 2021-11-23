package database;

import accommodations.Accommodation;
import communication.Review;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccommodationReviews extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private final HashMap<Accommodation, ArrayList<Review>> accommodationReviews; // DB holding reviews a user writes

    public AccommodationReviews() {
        super("src/config/accommodationReviews.ser");
        //noinspection Convert2Diamond
        accommodationReviews = new HashMap<Accommodation, ArrayList<Review>>();
    }


    public void insertReviewToAccommodation(Accommodation accommodation, Review review) {
        if (accommodation == null || review == null)
            return;
        ArrayList<Review> reviewList = accommodationReviews.get(accommodation);
        if (reviewList == null)
            reviewList = new ArrayList<>();
        reviewList.add(review);
        accommodationReviews.put(accommodation, reviewList);
    }

    public List<Review> selectReviewFromAccommodation(Accommodation accommodation) {
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
