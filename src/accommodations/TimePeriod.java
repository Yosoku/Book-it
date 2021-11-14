package accommodations;

import java.sql.Time;
import java.time.LocalDateTime;

public class TimePeriod implements Comparable<TimePeriod> {

    private final LocalDateTime start;
    private final LocalDateTime end;


    public TimePeriod(LocalDateTime start, LocalDateTime end) {
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
     * Returns an integer based on the timelinen between the periods considering *ONLY* the starting Date of each period
     *
     * @param other another TimePeriod object to compare
     * @return -1 this.start<other.start
     * 1 other.start>this.start
     * 0 this.start=other.start
     */
    @Override
    public int compareTo(TimePeriod other) {
        return start.compareTo(other.start) + end.compareTo(other.end);
    }


    public boolean intersects(TimePeriod other) {
        if (start.isEqual(other.start) || end.isEqual(other.end)) // [(-)] , [(-]) , (-[-)],[-(-)]
            return true;
        if (start.isBefore(other.start)) // -------[-------(
        {
            if (end.isBefore(other.start)) // -------[]---(----
                return false;
            if (end.isAfter(other.start)) // ---[--(---]---
                return true;
        } else {                           // ----(--[
            if (other.end.isBefore(start)) // ---(--)--[--]
                return false;
            if (start.isBefore(other.end)) // ---(-[--)-]
                return true;
        }
        return false;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    @Override
    public String toString() {
        return "TimePeriod{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}
