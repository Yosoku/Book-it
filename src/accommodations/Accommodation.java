package accommodations;

import communication.Review;
import users.User;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


public class Accommodation {

    private int space; // square meters
    private String address;
    private User owner; // Owner can be either a broker or just another user
    private List<ImageIcon> images;
    private String description;    //Brief description of the accomodation
    private List<Review> reviews; // User reviews



    //calendar sth


    public Accommodation(int space, String address, User owner, List<ImageIcon> images, String description) {
        this.space = space;
        this.address = address;
        this.owner = owner;
        this.images = images;
        this.description = description;
        reviews = new ArrayList<Review>();
    }

    //========================== Getters & Setters =============================================
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

    public List<Review> getReviews() {
        return reviews;
    }
    public void addReview(Review rev){
        reviews.add(rev);
    }

    @Override
    public String toString() {
        return "Accommondation{" +
                "space=" + space +
                ", address='" + address + '\'' +
                ", owner=" + owner +
                ", images=" + images +
                ", description='" + description + '\'' +
                '}';
    }
}
