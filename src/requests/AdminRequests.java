package requests;

import UI.AdminUI;
import application.DatabaseAPI;
import communication.Message;
import users.Admin;
import users.User;

public class AdminRequests implements Handler {
    private AdminUI ui;
    private Admin admin;

    public AdminRequests(Admin admin) {
        ui = new AdminUI(admin);
        this.admin = admin;

    }

    @Override
    public void handleRequests() {
        boolean quit = false;
        while (!quit) {
            ui.show();
            switch (ui.getRequest()) {
                case "confirm" -> {
                    String confirmationMessage = "Hello reply to this email to confirm your account";
                    for (User user : DatabaseAPI.getUserConfirmationsDatabase().selectAllUsersWhereConfirmedIs(false)) {
                        System.out.println("Send confirmation message to user " + user.toString() + "\ny/n");
                        char ans = ui.getInput("y/n", "y|Y|N|n").toLowerCase().charAt(0);
                        if (ans == 'y')
                            DatabaseAPI.getUserMessagesDatabase().insertMessageToUser(user, new Message(admin, user, confirmationMessage));
                    }
                }
                case "stats" -> {
                    System.out.println("Total number of users : " + DatabaseAPI.getUserConfirmationsDatabase().selectAllUsers().size());
                    System.out.println("Total number of accommodations : " + DatabaseAPI.getBrokerAccommodationsDatabase().selectAllAccommodations().size());
                }
                case "signout" -> quit = true;

            }
        }
    }
}