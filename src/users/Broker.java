package users;

import accommodations.Accommodation;

import java.util.ArrayList;
import java.util.List;

public class Broker extends User {
    private String company;
    private List<Accommodation> properties;

    public Broker(String name, int age, String username, String email, Gender gender, String phone, String password, String company) {
        super(name, age, username, email, gender, phone, password);
        this.company = company;
        properties = new ArrayList<Accommodation>();
    }

    public List<Accommodation> getProperties() {
        return properties;
    }

    public void addProperty(Accommodation acc) {
        if (acc != null)
            properties.add(acc);
    }

    public void removeProperty(Accommodation delProperty) {
        properties.remove(delProperty);
    }

    public void editProperty(Accommodation accToEdit) {
        if (properties.contains(accToEdit))
            //edit
            System.out.println("Property is available to edit");

    }

    @Override
    public String toString() {
        return "Broker{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                '}';
    }
}
