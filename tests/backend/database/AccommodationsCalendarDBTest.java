package backend.database;

import backend.accommodations.Accommodation;
import backend.accommodations.TimePeriod;
import backend.application.DatabaseAPI;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class AccommodationsCalendarDBTest {
    private TimePeriod period;
    private Accommodation accommodation;
    private AccommodationsCalendarDB calendarDB;

    @BeforeEach
    void setUp() {
        DatabaseAPI.loadData();
        calendarDB = DatabaseAPI.accommodationsCalendarDatabase;
        period = new TimePeriod(LocalDate.of(2022, 10, 10),
                LocalDate.of(2022, 10, 11));
        accommodation = new Accommodation(0, "test", "test", null, "test", 0);
    }

    @AfterEach
    void tearDown() {
        DatabaseAPI.writeData();
    }

    @Test
    void insertTimePeriodToAccommodation() {

        calendarDB.insertTimePeriodToAccommodation(accommodation, period);
        TreeSet<TimePeriod> response = calendarDB.selectCalendarFromAccommodation(accommodation);
        assertNotEquals(response, null);
        assertEquals(response.size(), 1);
        calendarDB.dropTimePeriodFromAccommodation(accommodation, period);
        calendarDB.insertTimePeriodToAccommodation(null, null);
        response = calendarDB.selectCalendarFromAccommodation(null);
        assertEquals(response.size(), 0);
    }

    @Test
    void accommodationAvailableInTimePeriod() {

        calendarDB.insertTimePeriodToAccommodation(accommodation, period);
        assertFalse(calendarDB.accommodationAvailableInTimePeriod(accommodation, period));
        calendarDB.dropTimePeriodFromAccommodation(accommodation, period);
        TimePeriod period1 = new TimePeriod(LocalDate.of(2022, 10, 12),
                LocalDate.of(2022, 10, 13));
        calendarDB.insertTimePeriodToAccommodation(accommodation, period1);
        assertTrue(calendarDB.accommodationAvailableInTimePeriod(accommodation, period));
        calendarDB.dropTimePeriodFromAccommodation(accommodation, period1);
    }

    @Test
    void selectCalendarFromAccommodation() {
        calendarDB.insertTimePeriodToAccommodation(accommodation, period);
        assertEquals(calendarDB.selectCalendarFromAccommodation(accommodation).size(), 2);
        calendarDB.dropTimePeriodFromAccommodation(accommodation, period);
        assertTrue(calendarDB.selectCalendarFromAccommodation(null).isEmpty());

    }

    @Test
    void dropTimePeriodFromAccommodation() {
        calendarDB.dropTimePeriodFromAccommodation(accommodation, period);
        assertEquals(calendarDB.selectCalendarFromAccommodation(accommodation).size(), 1);
        calendarDB.insertTimePeriodToAccommodation(accommodation, period);
        calendarDB.dropTimePeriodFromAccommodation(null, null);
        calendarDB.dropTimePeriodFromAccommodation(accommodation, null);
    }
}