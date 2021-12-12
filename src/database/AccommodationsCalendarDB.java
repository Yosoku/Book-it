package database;

import accommodations.Accommodation;
import accommodations.TimePeriod;

import java.util.HashMap;
import java.util.TreeSet;

public class AccommodationsCalendarDB extends Database {
    private HashMap<Accommodation, TreeSet<TimePeriod>> accommodationsCalendar;

    /**
     * A basic constructor to initialize the filename. Often used in kid classes in the form of super("file/path/to/store/objects");
     */
    public AccommodationsCalendarDB() {
        super("src/config/accommodationsCalendar.ser");
        accommodationsCalendar = new HashMap<Accommodation, TreeSet<TimePeriod>>();
    }


    public void insertTimePeriodToAccommodation(Accommodation accommodation, TimePeriod timePeriod) {
        if (timePeriod == null || accommodation == null)
            return;
        TreeSet<TimePeriod> calendar = accommodationsCalendar.get(accommodation);
        if (calendar == null)
            calendar = new TreeSet<TimePeriod>();
        calendar.add(timePeriod);
        accommodationsCalendar.put(accommodation, calendar);
    }

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

    public TreeSet<TimePeriod> selectCalendarFromAccommodation(Accommodation accommodation) {
        TreeSet<TimePeriod> calendar = new TreeSet<>();
        if (accommodation == null)
            return calendar;
        if (accommodationsCalendar.get(accommodation) == null)
            return calendar;
        return accommodationsCalendar.get(accommodation);

    }
}

