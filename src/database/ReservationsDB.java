package database;

import accommodations.Accommodation;
import accommodations.Reservation;
import users.User;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashSet;

public class ReservationsDB extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private HashSet<Reservation> reservations;

    /**
     * A basic constructor to initialize the filename. Often used in kid classes in the form of super("file/path/to/store/objects");
     */
    public ReservationsDB() {
        super("src/config/reservations.ser");
        reservations = new HashSet<Reservation>();
    }


    /**
     * A query for inserting new Reservations in the Database. If any of the parameters is <b>null</b> the method will exit
     * without changing the HashSet. Runs in O(1) time
     *
     * @param newReservation The new Reservation to insert in the HashSet
     */
    public void insertReservation(Reservation newReservation) {
        if (newReservation == null)
            return;
        reservations.add(newReservation);
    }

    /**
     * A query for selecting all Reservations by a specific User. Runs in O(r) time where r = Number of Reservations
     *
     * @param user The user arguement used we need the Reservations from
     * @return An ArrayList of all the Reservations the User has added. If the User arguement is null the method returns
     * an empty ArrayList
     */
    public ArrayList<Reservation> selectReservationsByUser(User user) {
        ArrayList<Reservation> temp = new ArrayList<Reservation>();
        if (user == null)
            return temp;
        for (Reservation reservation : reservations) {
            if (reservation.user().equals(user))
                temp.add(reservation);
        }
        return temp;
    }

    /**
     * A query for selecting Reservations in a specific Accommodation. Runs in O(r) time where r = Number of reviews
     *
     * @param accommodation The Accommodation instance used to Query the HashSet
     * @return An ArrayList of all the Reservations made on the accommodation. If the accommodation arguement is null the method
     * returns an empty ArrayList
     */
    public ArrayList<Reservation> selectReservationsByAccommodation(Accommodation accommodation) {
        ArrayList<Reservation> temp = new ArrayList<Reservation>();
        if (accommodation == null)
            return temp;
        for (Reservation reservation : reservations) {
            if (reservation.accommodation().equals(accommodation))
                temp.add(reservation);
        }
        return temp;
    }

    public void dropAllReservationsByAccommodation(Accommodation accommodation) {
        if (accommodation == null)
            return;
        selectReservationsByAccommodation(accommodation).clear();
    }

    public void dropAllReservationsByUser(User user) {
        if (user == null)
            return;
        selectReservationsByUser(user).clear();
    }


}
