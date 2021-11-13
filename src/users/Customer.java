package users;

import accommondations.Accommondation;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{

    private List<Accommondation> favorites;

    public Customer(String name, int age, String username, String email, Gender gender, String phone) {
        super(name, age, username, email, gender, phone);
        favorites = new ArrayList<Accommondation>();
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
