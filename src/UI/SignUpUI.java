package UI;

import auth.Credentials;
import users.*;
/**
 * A class used for sign up
 *
 * @author Fwteinos Wawaroutas
 */
public class SignUpUI extends UI {
    private User newUser;

    /**
     * Shows the signup UI
     */
    public void show() {
        String[] temp;
        temp = getInput("Enter your username and password separated by white space",
                "\\b[a-zA-Z]+\\s[a-zA-Z]+\\b").split("\\s+");
        Credentials credentials = new Credentials(temp[0], temp[1]);
        String name = getInput("Enter your first name,this will not be your username", "^[a-zA-Z'-]+$");
        int age = Integer.parseInt(getInput("Whats your age?", "^\\d{2}$"));
        String email;
        email = getInput("Enter your email address",
                "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=^_`{|}~-]+)*" +
                        "|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]" +
                        "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
                        "@" +
                        "(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" +
                        "|\\[(?:(?:(2(5[0-5]|[0-4][0-9])" +
                        "|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])\n" +
                        "|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:" +
                        "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]" +
                        "|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");   //Don't hate the player,hate the game
        String phone =
                getInput("Enter a 10 digit number which will be used as your contact phone", "^\\d{10}$");
        Privilege privilege = Privilege.getPrivileges(
                getInput("Are you a Broker or a customer?Enter either broker or customer lowercase",
                        "(customer|broker)"));
        Gender gender =
                Gender.getGender(getInput("Are you a Male or Female? Enter either male or female lowercase",
                        "(female|male)"));

        if (privilege.toString().equals("broker"))
            newUser = new Broker(credentials, name, age, email, gender, phone, "");
        else
            newUser = new Customer(credentials, name, age, email, gender, phone);

    }

    /**
     * returns the newUser that signed up
     */
    public User getNewUser() {
        return newUser;
    }
}


