package UI;

import java.util.concurrent.TimeUnit;

public class WelcomeUI extends UI {
    @Override
    public void show() {
        int readDelay = 2;
        System.out.println("Welcome to the Alpha version of Booking Clone");
        try {
            TimeUnit.SECONDS.sleep(readDelay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The application currently features over 100.000 users.\nPlease make sure to read the README" +
                " as it provides useful information and a variety of admin accounts");
        try {
            TimeUnit.SECONDS.sleep(readDelay + 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Please note that the features provided to the Users are limited,as they are a work in progress");
        try {
            TimeUnit.SECONDS.sleep(readDelay + 1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Booking clone has been thoroughly tested,if you find any bugs please contact the developer\n" +
                "> edouardos@csd.auth.gr");
        try {
            TimeUnit.SECONDS.sleep(readDelay + 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("The application will now launch. Please enjoy");
        try {
            TimeUnit.SECONDS.sleep(readDelay + 4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
