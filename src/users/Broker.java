package users;

import auth.Credentials;

import java.io.Serial;

/**
 * <p>
 * A Broker class representing a Broker in an application. The Broker class inherits from the abstract Class User
 * resulting in inheritance of all the fields (Credentials, Name, Age,Email,Gender, Phone number) and its getter/setter
 * methods for each field. In addition it holds a field company used to store the name of the company the Broker instance works at.
 * It also provides a toString() override useful for printing a Broker class to a stream
 * </p>
 *
 * @author Edward Koulakidis
 * @see User
 */
public class Broker extends User {
    private final String company;
    @Serial
    private static final long serialVersionUID = 0;

    public Broker(Credentials credentials, String name, int age, String email, Gender gender, String phone, String company) {
        super(credentials, name, age, email, gender, phone, Privilege.BROKER);
        this.company = company;
    }

    /**
     * An override of toString() method useful for debugging/printing information
     *
     * @return A String representation of a Broker instance
     */
    @Override
    public String toString() {
        return "Broker{" +
                "company='" + company + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", username='" + credentials.username() + '\'' +
                ", password='" + credentials.password() + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                '}';
    }
}
