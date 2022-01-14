package backend.UI;

import backend.users.User;

/**
 * A User Interface class shown to Users with Admin privileges
 *
 * @author Fwteinos Wawaroutas
 */
@Deprecated
public class AdminUI extends UI {
    private User admin;

    /**
     * Initializer constructor
     *
     * @param admin The current Admin
     */
    public AdminUI(User admin) {
        this.admin = admin;

        System.out.println("Welcome back " + admin.getName() + ",how would you like to proceed?");

    }

    /**
     * Method that prints all available options to the current User
     */
    @Override
    public void show() {
        System.out.println("\n---Options---");
        System.out.println(">Confirm new Users(confirm)\n>View statistics(stats)\n>View Inbox\n>Sign out(signout)");
        request = getInput("Enter confirm/stats/inbox/signout", "(inbox|confirm|stats|signout)");
    }


}
