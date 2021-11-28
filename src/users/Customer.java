package users;

import auth.Credentials;

import java.io.Serial;

/**
 * <p>
 * A customer class representing a Customer in an application. The customer class inherits from the abstract Class User
 * resulting in inheritance of all the fields (Credentials, Name, Age,Email,Gender, Phone number) and its getter/setter
 * methods for each field. It also provides a toString() override useful for printing a Customer class to a stream
 * </p>
 *
 * @author Edward Koulakidis
 * @see User
 */

public class Customer extends User {
    @Serial
    private static final long serialVersionUID = 0;

    public Customer(Credentials credentials, String name, int age, String email, Gender gender, String phone) {
        super(credentials, name, age, email, gender, phone, Privilege.CUSTOMER);
    }

    /**
     * An override of toString() method useful for debugging/printing information
     *
     * @return A String representation of a Customer instance
     */
    @Override
    public String toString() {
        return "Customer{" +
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
