package accommodations;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;


public class Accommodation implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;
    private int ID;
    private int space; // square meters
    private String address;
    private List<ImageIcon> images;
    private String description;    //Brief description of the accommodation
    private int price; // Price per night
    private boolean available; // Availability of the accommodation depending on renovations etc
    private final List<Reservation> reservations;
    private final TreeSet<TimePeriod> calendar;




    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public Accommodation(int space, String address, List<ImageIcon> images, String description,
                         int price, boolean available) {
        this.space = space;
        this.address = address;
        this.images = images;
        this.description = description;
        this.price = price;
        this.available = available;
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


    public List<ImageIcon> getImages() {
        return images;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "ID=" + ID +
                ", space=" + space +
                ", address='" + address + '\'' +
                ", images=" + images +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", available=" + available +
                ", reservations=" + reservations +
                ", calendar=" + calendar +
                '}';
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

}
