package UI;

import application.Application;

public class WelcomeUI extends UI {
    @Override
    public void show() {
        int readDelay = 2;
        System.out.println("Welcome to the Alpha version of Booking Clone");
        Application.sleepFor(readDelay);
        System.out.println("The application currently features over 100.000 users.\nPlease make sure to read the README" +
                " as it provides useful information and a variety of admin accounts");
        Application.sleepFor(readDelay + 2);
        System.out.println("Please note that the features provided to the Users are limited,as they are a work in progress");
        Application.sleepFor(readDelay + 1);
        System.out.println("Booking clone has been thoroughly tested,if you find any bugs please contact the developer\n" +
                "> edouardos@csd.auth.gr");
        Application.sleepFor(readDelay + 3);
        System.out.println("The application will now launch. Please enjoy");
        Application.sleepFor(readDelay + 4);
    }
}
