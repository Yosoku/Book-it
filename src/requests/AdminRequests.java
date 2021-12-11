package requests;

import UI.AdminUI;
import application.DatabaseAPI;
import communication.Message;
import users.Admin;
import users.User;

import java.util.List;

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
                    String confirmationMessage = "Hello read this email to confirm your account";
                    List<User> unconfirmed = DatabaseAPI.getUserConfirmationsDatabase().selectAllUsersWhereConfirmedIs(false);
                    System.out.printf("There are currently %d unconfirmed users. Would you like to confirmed them all?\n"
                            , unconfirmed.size());
                    char ans = ui.getInput("y/n", "y|Y|n|N").charAt(0);
                    if (ans == 'y') {
                        System.out.println("Sending confirmation message to all Unconfirmed Users...");
                        unconfirmed.forEach(user -> DatabaseAPI.getUserMessagesDatabase().
                                insertMessageToUser(user, new Message(admin, user, Message.auth, confirmationMessage)));
                    } else {
                        for (User user : unconfirmed) {
                            System.out.printf("Send confirmation message to user? q to exit\n %s", user.toString());
                            ans = ui.getInput("y/n/q", "q|Q|y|Y|N|n").toLowerCase().charAt(0);
                            if (ans == 'y')
                                DatabaseAPI.getUserMessagesDatabase().insertMessageToUser
                                        (user, new Message(admin, user, Message.auth, confirmationMessage));
                            if (ans == 'q')
                                break;
                        }
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