package accommodations;

import javax.swing.*;
import java.io.Serial;
import java.io.Serializable;
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
    private TreeSet<TimePeriod> calendar;


    public Accommodation(int space, String address, List<ImageIcon> images, String description, int price) {
        this.space = space;
        this.address = address;
        this.images = images;
        this.description = description;
        this.price = price;
        calendar = new TreeSet<TimePeriod>();
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
                ", calendar=" + calendar +
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


    public void addToCalendar(TimePeriod timePeriod) {
        if (timePeriod == null)
            return;
        calendar.add(timePeriod);
    }

    public void removeFromCalendar(TimePeriod timePeriod) {
        if (timePeriod == null)
            return;
        calendar.remove(timePeriod);
    }

    /**
     * isOccupied is a method that helps with reservations and gives a higher level interface for users. Occupation is
     * NOT the same as availability(field:available). Occupied is only related to a Time period where an accommodation
     * has someone living/not living in it
     *
     * @param period the time period we wish to check if the accommodation is free to reserve
     * @return true if the accommodation is reserved for the time period @param,false otherwise
     */
    public boolean isAvailable(TimePeriod period) {
        TimePeriod temp = calendar.floor(period); // temp.start<=period.start

        if (temp == null) {
            if (calendar.isEmpty())
                return !false;
        } else {
            return !period.intersects(temp);
        }
        return !false;
    }

}
