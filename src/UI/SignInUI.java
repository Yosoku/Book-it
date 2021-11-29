package UI;

public class SignInUI extends UI {


    //TODO fix regex doesnt support passwords ending with numbers
    public void show() {
        request = getInput("Enter your username and password separated by white space",
                "\\b[a-zA-Z]+\\s[a-zA-Z]+\\b");
    }

}
