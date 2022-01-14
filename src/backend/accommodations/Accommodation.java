package backend.accommodations;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.List;


/**
 * A class for representing Accommodations in an Application. Each Accommodation instance provides information about
 * location,pricing,space and a description. The class provides a unique ID assigned by an API call to the DatabaseAPI.
 * It implements the Serializable interface so it can be stored in files.
 */

public class Accommodation implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;
    private int ID;
    private int space; // square meters
    private String city;
    private String address;
    private List<ImageIcon> images;
    private String description;    //Brief description of the accommodation
    private int price; // Price per night

    /**
     * Initializer constructor
     *
     * @param space       The space of the accommodation in square meters
     * @param city        The city the accommodation is located in
     * @param address     The exact address of the accommodation
     * @param images      A list of images of the accommodation
     * @param description A brief description of the accommodation
     * @param price       The price per night of the accommodation
     */
    public Accommodation(int space, String city, String address, List<ImageIcon> images, String description, int price) {
        this.space = space;
        this.city = city;
        this.address = address;
        this.images = images;
        this.description = description;
        this.price = price;
    }

    /**
     * Useful for printing information to the User or debugging
     *
     * @return A string representation of the accommodation instance
     */
    @Override
    public String toString() {
        return "Accommodation{" +
                "ID=" + ID +
                ", space=" + space +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", images=" + images +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    /**
     * isOccupied is a method that helps with reservations and gives a higher level interface for users. Occupation is
     * NOT the same as availability(field:available). Occupied is only related to a Time period where an accommodation
     * has someone living/not living in it
     *
     * @param period the time period we wish to check if the accommodation is free to reserve
     * @return true if the accommodation is reserved for the time period @param,false otherwise
     */


}
