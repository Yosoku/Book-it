package UI;

/**
 * A class used for signing in
 *
 * @author Fwteinos Wawaroutas
 */
public class SignInUI extends UI {

    /**
     * Shows the signin UI
     */
    public void show() {
        request = getInput("Enter your username and password separated by white space",
                "\\w*[a-zA-Z]\\w*+\\s\\w*[a-zA-Z]\\w*+\\b");
    }

}
