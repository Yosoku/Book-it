package UI;

import users.User;

public class AdminUI extends UI {
    private User admin;

    public AdminUI(User admin) {
        this.admin = admin;
        System.out.println("Welcome back " + admin.getName() + ",how would you like to proceed?");

    }

    @Override
    public void show() {
        System.out.println("\n---Options---");
        System.out.println(">Confirm new Users(confirm)\n>View statistics(stats)\n>Sign out(signout)");
        request = getInput("Enter confirm/stats/signout", "(confirm|stats|signout)");
    }


}
