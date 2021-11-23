package users;

import auth.Credentials;

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
    public Admin(Credentials credentials, String name, int age, String email, Gender gender, String phone) {
        super(credentials, name, age, email, gender, phone, Privilege.ADMIN);
    }
}

