package users;

import accommodations.Accommodation;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private final List<Accommodation> favorites; // Users favorite accommodations


    public Customer(String name, int age, String username, String email, Gender gender, String phone, String password) {
        super(name, age, username, email, gender, phone, password);
        favorites = new ArrayList<Accommodation>();

    }


    public void addFavorite(Accommodation newFavorite) {
        if (newFavorite != null)
            favorites.add(newFavorite);
    }

    public void removeFavorite(Accommodation delFavorite) {
        favorites.remove(delFavorite);
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
