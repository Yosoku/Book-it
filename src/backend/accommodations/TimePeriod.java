package backend.accommodations;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A record for repersenting a Time Period. It holds information about the start/end LocalDate and a method
 * for checking intersectings between 2 time period instances.The record implements the Serializable interface,for
 * file storage and also the Comparable interface,which is useful for storing the TimePeriods in an ordered Set
 *
 * @author Edward Koulakidis
 */
public record TimePeriod(LocalDate start, LocalDate end) implements Serializable, Comparable<TimePeriod> {
    @Serial
    private static long SerialVersionUID = 0;

    /**
     * Initializer constructor which makes sure the start date is before the end date
     *
     * @param start starting Date
     * @param end   ending Date
     */
    public TimePeriod(LocalDate start, LocalDate end) {
        if (start.isBefore(end)) { //normal
            this.start = start;
            this.end = end;
        } else if (start.isAfter(end)) { //reversed
            this.start = end;
            this.end = start;
        } else { //equal
            this.start = start;
            this.end = end;
        }
    }


    /**
     * Returns an integer based on the timeline between the periods considering the starting and ending
     * Date of each period
     *
     * @param other another TimePeriod object to compare
     * @return The result of LocalDate.compareTo of start + end
     */
    @Override
    public int compareTo(TimePeriod other) {
        return start.compareTo(other.start) + end.compareTo(other.end);
    }


    /**
     * Method for checking if 2 TimePeriod instances intersect. Is it confusing you?Good,it should. Do not touch it
     *
     * @param other TimePeriod to check for intersection
     * @return True if the 2 periods intersect,false otherwise
     */
    public boolean intersects(TimePeriod other) {
        if (start.isEqual(other.start) || end.isEqual(other.end)) // [(-)] , [(-]) , (-[-)],[-(-)]
            return true;
        if (start.isBefore(other.start)) // -------[-------(
        {
            if (end.isBefore(other.start)) // -------[]---(----
                return false;
            // ---[--(---]---
            return end.isAfter(other.start);
        } else {                           // ----(--[
            if (other.end.isBefore(start)) // ---(--)--[--]
                return false;
            // ---(-[--)-]
            return start.isBefore(other.end);
        }
    }

    /**
     * Calculates and returns the number of days in a TimePeriod. E.g. 1/1/2022 - 2/1/2022 has 2 days in it
     *
     * @return the number of days in TimePeriod
     */
    public int getNumberOfDays() {
        return end.getDayOfMonth() - start.getDayOfMonth() + 1;
    }

    /**
     * Although the class allows for a TimePeriod instance having start==date,this method provides a way to detect and
     * delete any invalid TimePeriods
     *
     * @return true if the TimePeriod is INVALID,false if it is VALID
     */
    public boolean isInvalid() {
        return start == end;
    }

    /**
     * Useful for printing to the user and also debugging
     *
     * @return a string representation of the TimePeriod instance
     */
    @Override
    public String toString() {
        return start + " till " + end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimePeriod that = (TimePeriod) o;
        return Objects.equals(start, that.start) && Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }
}
