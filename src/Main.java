import accommodations.Accommodation;
import accommodations.Reservation;
import accommodations.TimePeriod;
import communication.Messenger;
import users.Broker;
import users.Customer;
import users.User;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) {
        Accommodation ac = new Accommodation
                (100, "Xarilao", null, null, "", 200, true);
        User user = new Customer("Makis", 30, "makis123",
                "makis@com", User.Gender.FEMALE, "6982093778", "password123");


        LocalDateTime dt1 = LocalDateTime.of(2020, 11, 14, 12, 0);
        LocalDateTime dt2 = LocalDateTime.of(2020, 11, 17, 12, 0);
        TimePeriod period1 = new TimePeriod(dt1, dt2);
        if (!ac.isOccupied(period1))
            ac.addReservation(new Reservation(user, period1, "14/11 12:00 - 17/11 12:00"));

        LocalDateTime dt3 = LocalDateTime.of(2020, 10, 19, 12, 0);
        LocalDateTime dt4 = LocalDateTime.of(2020, 11, 19, 15, 0);
        TimePeriod period2 = new TimePeriod(dt3, dt4);
        if (!ac.isOccupied(period2))
            ac.addReservation(new Reservation(user, period2, "19/10 12:00 - 19/11 15:00"));


        LocalDateTime dt5 = LocalDateTime.of(2020, 11, 12, 13, 0);
        LocalDateTime dt6 = LocalDateTime.of(2020, 11, 15, 12, 0);
        TimePeriod period3 = new TimePeriod(dt5, dt6);
        if (!ac.isOccupied(period2))
            ac.addReservation(new Reservation(user, period3, "ez 3"));


        for (Reservation res : ac.getReservations()) {
            System.out.println(res);
        }


        LocalDateTime target1 = LocalDateTime.of(2020, 8, 10, 12, 0);
        LocalDateTime target2 = LocalDateTime.of(2020, 9, 10, 12, 0);

    }
}
