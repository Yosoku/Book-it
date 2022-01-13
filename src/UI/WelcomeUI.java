package UI;

/**
 * class to show the welcome UI to the user
 */
@Deprecated
public class WelcomeUI extends UI {
    /**
     * shows the ui
     */
    @Override
    public void show() {
        int readDelay = 2;
        System.out.println("Welcome to the Alpha version of Booking Clone");

        System.out.println("The application currently features over 100.000 users.\nPlease make sure to read the README" +
                " as it provides useful information and a variety of admin accounts");

        System.out.println("Please note that the features provided to the Users are limited,as they are a work in progress");

        System.out.println("Booking clone has been thoroughly tested,if you find any bugs please contact the developer\n" +
                "> edouardos@csd.auth.gr");

        System.out.println("The application will now launch. Please enjoy");

    }
}
