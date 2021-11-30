package accommodations;

import users.User;


public record Reservation(User user, Accommodation accommodation, TimePeriod period, String comments)
        implements Comparable<Reservation> {

    @Override
    public int compareTo(Reservation o) {
        return period.compareTo(o.period());
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "user=" + user.getName() +
                ", period=" + period +
                ", comments='" + comments + '\'' +
                '}';
    }
}
