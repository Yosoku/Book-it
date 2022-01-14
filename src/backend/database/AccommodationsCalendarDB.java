package backend.database;

import backend.accommodations.Accommodation;
import backend.accommodations.TimePeriod;

import javax.swing.*;
import java.util.HashMap;
import java.util.TreeSet;


/**
 * <p>
 * The AccommodationsCalendar is a class intended to handle information about the Calendar of Reservations an Accommodation
 * instance has in an application. It is implemented by a Hashtable holding Accommodations as keys and a <b>TreeSet of TimePeriods</b>
 * as values</p>
 * <p>
 * The AccommodationsCalendar inherits from Database inheriting read/write methods and implementing Serializable interface. It also
 * provides basic queries like SELECT,INSERT,UPDATE and DROP. It is also important to note that <b>null</b> values are not stored
 * in this class</p>
 *
 * @author Edward Koulakidis
 * @see java.io.Serializable
 * @see Database
 */

public class AccommodationsCalendarDB extends Database {
    private HashMap<Accommodation, TreeSet<TimePeriod>> accommodationsCalendar;

    /**
     * An initializer constructor used to initialize the map and call the Base class withthe specified filename to store
     * the map. <b>The Treeset of each Accommodation key gets initialized after the first entry</b>
     */

    public AccommodationsCalendarDB() {
        super("src/config/accommodationsCalendar.ser");
        accommodationsCalendar = new HashMap<Accommodation, TreeSet<TimePeriod>>();
    }

    /**
     * A query for inserting new TimePeriod instances in the TreeSet of a Accommodation. If any of the parameters is
     * <b>null</b> the method will exit without changing the Hashmap. If timePeriod is the first entry this method also
     * initilizes the Hashset.Runs in O(logn) time
     *
     * @param timePeriod    The timePeriod to insert in the TreeSet
     * @param accommodation The accommodation that the timePeriod instance is referring to
     */
    public void insertTimePeriodToAccommodation(Accommodation accommodation, TimePeriod timePeriod) {
        if (timePeriod == null || accommodation == null)
            return;
        TreeSet<TimePeriod> calendar = accommodationsCalendar.get(accommodation);
        if (calendar == null)
            calendar = new TreeSet<TimePeriod>();
        calendar.add(timePeriod);
        accommodationsCalendar.put(accommodation, calendar);
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started writing " + this);
                write();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished writing Databases");
            }
        }.execute();
    }

    /**
     * Method for checking if an Accommodation is available to book in a certian TimePeriod instance
     *
     * @param accommodation The accommodation to query
     * @param timePeriod    The TimePeriod instance to check if the accommodation is available
     * @return true if the accommodation is available to book,false otherwise
     */
    public boolean accommodationAvailableInTimePeriod(Accommodation accommodation, TimePeriod timePeriod) {
        if (accommodation == null || timePeriod == null)
            return false;
        if (accommodationsCalendar.get(accommodation) == null) //null means 0 entries so its available
            return true;
        TimePeriod temp = accommodationsCalendar.get(accommodation).floor(timePeriod); // temp.start<=period.start
        if (temp == null) {
            if (accommodationsCalendar.get(accommodation).isEmpty())
                return true;
        } else {
            return !timePeriod.intersects(temp);
        }
        return true;
    }

    /**
     * A query for selecting Calendars based on their Accommodation If the accommodation is found in the map the method
     * returns the TreeSet of TimePeriods,an empty TreeSet otherwise. Runs in O(1) time
     *
     * @param accommodation The accommodation used to query the HashMap
     * @return The TreeSet of TimePeriod instances regarding accommodation.Effectively returns the Calendar of booked dates
     */
    public TreeSet<TimePeriod> selectCalendarFromAccommodation(Accommodation accommodation) {
        TreeSet<TimePeriod> calendar = new TreeSet<>();
        if (accommodation == null)
            return calendar;
        if (accommodationsCalendar.get(accommodation) == null)
            return calendar;
        return accommodationsCalendar.get(accommodation);

    }
}

