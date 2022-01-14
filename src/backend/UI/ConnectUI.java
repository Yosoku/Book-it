package backend.UI;

/**
 * A class to Connect signin signup
 *
 * @author Fwteinos Wawaroutas
 */
@Deprecated
public class ConnectUI extends UI {


    /**
     * Shows the UI
     */
    @Override
    public void show() {
        System.out.println("It looks like you are not signed in. Would you like to sign up or sign in?");
        request = getInput("Type signin/signup to continue", "(signin|signup|quit)");
    }
}
