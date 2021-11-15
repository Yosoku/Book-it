package accommodations;

import communication.Review;
import users.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class Accommodation {

    private int space; // square meters
    private String address;
    private User owner; // Owner can be either a broker or just another user
    private List<ImageIcon> images;
    private String description;    //Brief description of the accommodation
    private final List<Review> reviews; // User reviews
    private final int price; // Price per night
    private boolean available; // Availability of the accommodation depending on renovations etc
    private final List<Reservation> reservations;
    private final TreeSet<TimePeriod> calendar;

    //calendar sth


    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Accommodation(int space, String address, User owner, List<ImageIcon> images, String description,
                         int price, boolean available) {
        this.space = space;
        this.address = address;
        this.owner = owner;
        this.images = images;
        this.description = description;
        this.price = price;
        this.available = available;

        reviews = new ArrayList<Review>();
        reservations = new ArrayList<Reservation>();
        calendar = new TreeSet<TimePeriod>();
    }

    //========================== Getters & Setters =====================================================================
    public int getSpace() {
        return space;
    }

    public void setSpace(int space) {
        this.space = space;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<ImageIcon> getImages() {
        return images;
    }

    public void setImages(List<ImageIcon> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method returns all the reviews this accommodation has regarding the stars
     *
     * @param atLeast the integer number of reviews the search returns. It's obvious that if atLeast = 0 all reviews are
     *                returned,and if atLeast>5 no reviews are returned
     * @return a List collection containing all the reviews that match the search
     */
    public List<Review> getReviews(int atLeast) {
        List<Review> temp = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getStars() >= atLeast)
                temp.add(review);
        }
        return temp;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    //==================================================================================================================

    public void addReservation(Reservation newReservation) {
        if (newReservation != null) {
            reservations.add(newReservation);
            calendar.add(newReservation.getPeriod());
        }
    }

    public void removeReservation(Reservation delReservation) {
        reservations.remove(delReservation);
    }

    public void addReview(Review rev) {
        if (rev != null)
            reviews.add(rev);
    }

    /**
     * isOccupied is a method that helps with reservations and gives a higher level interface for users. Occupation is
     * NOT the same as availability(field:available). Occupied is only related to a Time period where an accommodation
     * has someone living/not living in it
     *
     * @param period the time period we wish to check if the accommodation is free to reserve
     * @return true if the accommodation is reserved for the time period @param,false otherwise
     */
    public boolean isOccupied(TimePeriod period) {
        TimePeriod temp = calendar.floor(period); // temp.start<=period.start

        if (temp == null) {
            if (calendar.isEmpty())
                return false;
        } else {
            return period.intersects(temp);
        }
        return false;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "space=" + space +
                ", address='" + address + '\'' +
                ", owner=" + owner +
                ", images=" + images +
                ", description='" + description + '\'' +
                ", reviews=" + reviews +
                ", price=" + price +
                ", available=" + available +
                ", reservations=" + reservations +
                '}';
    }
}
