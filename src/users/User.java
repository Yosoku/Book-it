package users;

import auth.Credentials;
import communication.Message;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * An abstract User class for handling User information. This class exists as a convenience for creating and handling
 * different types of Users in an application.</p>
 * <p>
 * Each user has information about his name,age,Username and password(Credentials),his privileges(Admin,Customer,Broker)
 * ,his email address,gender and phone number.</p>
 * <p>Use this class as a blueprint to create different types of Users in an Application
 * which supports login/sign up functionality</p>
 * <p>This class also implements the Serializable interface in case you want to store a User object in a file</p>
 *
 * @author Edward Koulakidis
 * @see java.io.Serializable
 * @see Customer
 * @see Broker
 * @see Admin
 */

public abstract class User implements Serializable {
    /**
     * A basic constructor which takes the parameters below and initializes a User object
     * @param credentials A username/password combination
     * @param name The users name
     * @param age The users age
     * @param email The users email
     * @param gender The users gender
     * @param phone The users phone number
     * @param privilege The users privilege(Customer/Admin/Broker)
     * @see Credentials
     * @see Privilege
     */
    public User(Credentials credentials, String name, int age, String email, Gender gender, String phone, Privilege privilege) {
        this.name = name;
        this.age = age;
        this.credentials = credentials;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.privilege = privilege;
    }


    //=========================== Getters & Setters ====================================================================

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String readMessage(Message message) {
        message.setSeen(true);
        return message.getContents();
    }


    @Serial
    private static final long serialVersionUID = 0;
    protected String name;
    protected int age;
    protected Credentials credentials;
    protected final Privilege privilege;
    protected String email;
    protected Gender gender;
    protected String phone;
}
