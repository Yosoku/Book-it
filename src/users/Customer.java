package users;

import accommodations.Accommodation;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{

    private List<Accommodation> favorites;

    public Customer(String name, int age, String username, String email, Gender gender, String phone) {
        super(name, age, username, email, gender, phone);
        favorites = new ArrayList<Accommodation>();
    }

    @Override
    public String toString() {
        return "Customer{" +
                "favorites=" + favorites +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                '}';
    }
}
