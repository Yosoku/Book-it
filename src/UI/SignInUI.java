package UI;

public class SignInUI extends UI {


    public void show() {
        request = getInput("Enter your username and password separated by white space",
                "\\b[a-zA-Z]+\\s[a-zA-Z]+\\b");
    }

}
