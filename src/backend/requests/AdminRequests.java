package backend.requests;

import backend.application.DatabaseAPI;
import backend.application.Server;
import backend.communication.Message;
import backend.users.Admin;
import backend.users.User;
import config.Configurations;
import frontend.AppWindow;
import frontend.Inbox.InboxView;
import frontend.usersgui.AdminHomePage;
import frontend.usersgui.ProfileView;

import javax.swing.*;
import java.util.List;

/**
 * A class representing a Request Handler for Administrator Requests. It holds information about the current Admin making
 * requests and a Admin User Interface instance
 *
 * @author Edward Koulakidis
 */
public class AdminRequests implements Handler {
    private Admin admin;

    /**
     * Initializer constructor
     *
     * @param admin The Admin making requests
     */
    public AdminRequests(Admin admin) {
        this.admin = admin;
        AppWindow.getInstance().changePanel(new AdminHomePage(admin));
    }

    /**
     * The main method for handling Admin requests. It handles requests for viewing stats and confirming Users
     */
    @Override
    public void handleRequests() {
        switch (Server.getCurrentRequest()) {
            case "confirmall" -> {
                List<User> unconfirmed = DatabaseAPI.userConfirmationsDatabase.selectAllUsersWhereConfirmedIs(false);
                int response = JOptionPane.showConfirmDialog
                        (null, "This will send a confirmation email to " + unconfirmed.size() + " Users",
                                "Are you sure", JOptionPane.YES_NO_CANCEL_OPTION);
                if (response == JOptionPane.OK_OPTION) {
                    for (User user : unconfirmed) {
                        DatabaseAPI.userMessagesDatabase.insertMessageToUser
                                (user, new Message(admin, user, Configurations.CONFIRMATION_MESSAGE_SUBJECT,
                                        Configurations.CONFIRMATION_MESSAGE), false);
                    }
                    JOptionPane.showMessageDialog
                            (null, "All confirmations sent successfully",
                                    "Confirmation success", JOptionPane.INFORMATION_MESSAGE);
                }
                DatabaseAPI.userMessagesDatabase.write();
            }
            case "profile" -> SwingUtilities.invokeLater(() -> ProfileView.getInstance().setVisible(true));
            case "inbox" -> SwingUtilities.invokeLater(() -> {
                InboxView.closeView();
                InboxView.getInstance().setVisible(true);
            });
            case "signout" -> SwingUtilities.invokeLater(() -> AppWindow.getInstance().signOut());

        }
    }
}