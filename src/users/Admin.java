package users;

import auth.Credentials;

import java.io.Serial;

/**
 * <p>
 * A Admin class representing a Admin in an application. The Admin class inherits from the abstract Class User
 * resulting in inheritance of all the fields (Credentials, Name, Age,Email,Gender, Phone number) and its getter/setter
 * methods for each field. It also provides a toString() override useful for printing a Broker class to a stream
 * </p>
 *
 * @author Edward Koulakidis
 * @see User
 */
public class Admin extends User {
    @Serial
    private static final long serialVersionUID = 0;

    public Admin(Credentials credentials, String name, int age, String email, Gender gender, String phone) {
        super(credentials, name, age, email, gender, phone, Privilege.ADMIN);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", credentials=" + credentials +
                ", privilege=" + privilege +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                '}';
    }
}

