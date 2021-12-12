package accommodations;

import users.User;

import java.io.Serial;
import java.io.Serializable;

/**
 * A record representing a Reservation instance in a Booking Application. The record holds information about the TimePeriod
 * The user making the reservation,the Accommodation the reservation is held at and some comments about it.
 *
 * @author Edward Koulakidis
 */
public record Reservation(User user, Accommodation accommodation, TimePeriod period, String comments)
        implements Serializable, Comparable<Reservation> {

    @Serial
    private static final long serialVersionUID = 0;

    @Override
    public int compareTo(Reservation o) {
        return period.compareTo(o.period());
    }

    /**
     * Useful for printing to the User and Debugging
     *
     * @return a Stirng representation about the Reservation
     */
    @Override
    public String toString() {
        return "Reservation{" +
                "user=" + user +
                ", accommodation=" + accommodation +
                ", period=" + period +
                ", comments='" + comments + '\'' +
                '}';
    }
}
