package UI;

import application.Application;
import users.User;

/**
 * A User Interface class shown to Users with Admin privileges
 *
 * @author Fwteinos Wawaroutas
 */
public class AdminUI extends UI {
    private User admin;

    /**
     * Initializer constructor
     *
     * @param admin The current Admin
     */
    public AdminUI(User admin) {
        this.admin = admin;
        Application.sleepFor(1);
        System.out.println("Welcome back " + admin.getName() + ",how would you like to proceed?");
        Application.sleepFor(1);
    }

    /**
     * Method that prints all available options to the current User
     */
    @Override
    public void show() {
        System.out.println("\n---Options---");
        System.out.println(">Confirm new Users(confirm)\n>View statistics(stats)\n>Sign out(signout)");
        request = getInput("Enter confirm/stats/signout", "(confirm|stats|signout)");
    }


}
