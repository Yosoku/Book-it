package requests;

import GUI.AppWindow;
import GUI.Inbox.InboxView;
import GUI.ProfileView;
import GUI.usersgui.BrokerHomePage;
import UI.BrokerUI;
import application.DatabaseAPI;
import application.Server;
import users.Broker;

import javax.swing.*;

/**
 * A class representing a Request Handler for Broker Requests. It holds information about the current Broker making
 * requests and a Broker User Interface instance
 *
 * @author Edward Koulakidis
 */

public class BrokerRequests implements Handler {
    private Broker broker;
    private BrokerUI ui;

    /**
     * Initializer constructor
     *
     * @param broker The broker making requests
     */
    public BrokerRequests(Broker broker) {
        this.broker = broker;
        AppWindow.getInstance().changePanel(new BrokerHomePage(broker));
    }

    /**
     * The main method for handling Broker requests. It handles requests for viewing all accommodations,reservations
     * Adding/removing/edtting accommodations
     */
    @Override
    public void handleRequests() {


        switch (Server.getCurrentRequest()) {
            case "view" -> {


            }
            case "add" -> {

            }
            case "edit" -> {

            }
            case "delete" -> {


            }
            case "profile" -> {
                SwingUtilities.invokeLater(() -> {
                    JFrame profileView = new ProfileView(broker);
                    profileView.setVisible(true);
                });

            }
            case "inbox" -> {
                SwingUtilities.invokeLater(() -> {
                    InboxView inboxView = new InboxView(DatabaseAPI.userMessagesDatabase.selectMessageFromUser(broker));
                    inboxView.setVisible(true);
                });

            }
            case "signout" -> SwingUtilities.invokeLater(() -> AppWindow.getInstance().signOut());
        }
    }
}
