package database;

import accommodations.Accommodation;
import communication.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccommodationReviews extends Database {
    private final HashMap<Accommodation, ArrayList<Review>> accommodationReviews; // DB holding reviews a user writes

    public AccommodationReviews() {
        super(DatabaseType.ACCOMMODATION_REVIEWS, "");
        accommodationReviews = new HashMap<Accommodation, ArrayList<Review>>();
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

    @Override
    public void write() {

    }

    @Override
    public void read() {

    }
}
