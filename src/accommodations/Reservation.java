package accommodations;

import users.User;

import java.time.LocalDate;

public class Reservation implements Comparable<Reservation> {
    private User user;
    private TimePeriod period;
    private String comments;

    public Reservation(User user, TimePeriod period, String comments) {
        this.user = user;
        this.period = period;
        this.comments = comments;
    }

    //===================================== Getters & Setters ==========================================================
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TimePeriod getPeriod() {
        return period;
    }

    public void setPeriod(TimePeriod period) {
        this.period = period;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }


    @Override
    public int compareTo(Reservation o) {
        return period.compareTo(o.getPeriod());
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
