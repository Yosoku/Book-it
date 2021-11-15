import accommodations.Accommodation;
import accommodations.Reservation;
import accommodations.TimePeriod;
import auth.Encryption;
import users.Customer;
import users.User;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException {
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


        String password = "s7ycxdgdgfgdgdk443554fdfdfgdqi111224dfsfc";
        System.out.println(Encryption.SHA_512(password));
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        list1.add(10);
        list1.add(20);
        list1.add(30);
        map.put(1, list1);
        list1.add(40);
        for (Integer integer : map.get(1)) {
            System.out.println(integer);
        }
        list1.add(50);
        map.put(1, list1);
        for (Integer integer : map.get(1)) {
            System.out.println(integer);
        }


        //752a43e39a08b5bcaedf9302c82e78c8a8c9514755dd5de28fadf11aa7be2d8af8415c4acff7a403bf835c86fc1d16b3237d72a4dcde55443fb4657e28fc9d10
    }
}
