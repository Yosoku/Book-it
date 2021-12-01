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
        System.out.println("Welcome Admin\nHow would you like to proceed?");
        System.out.println("Confirm new Users(confirm)\nView statistics(stats)\nSign out(signout)");
        request = getInput("Enter confirm/stats", "(confirm|stats|signout)");
    }


}
