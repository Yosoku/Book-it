package UI;

/**
 * A class to Connect signin signup
 *
 * @author Fwteinos Wawaroutas
 */
public class ConnectUI extends UI {


    /**
     * Shows the UI
     */
    @Override
    public void show() {
        System.out.println("It looks like you are not signed in. Would you like to sign up or sign in?");
        request = getInput("Type signin/signup to continue or quit to save all progress and quit the application", "(signin|signup|quit)");
    }
}
