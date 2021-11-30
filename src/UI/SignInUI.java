package UI;

public class SignInUI extends UI {



    public void show() {
        request = getInput("Enter your username and password separated by white space",
                "\\w*[a-zA-Z]\\w*+\\s\\w*[a-zA-Z]\\w*+\\b");
    }

}
