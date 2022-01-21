package backend.requests;

import backend.accommodations.Accommodation;
import backend.application.Application;
import backend.application.DatabaseAPI;
import backend.application.Server;
import backend.users.Broker;
import frontend.AccommodationUI.AccommodationView;
import frontend.AppWindow;
import frontend.Inbox.InboxView;
import frontend.usersgui.BrokerHomePage;
import frontend.usersgui.ProfileView;

import javax.swing.*;

/**
 * A class representing a Request Handler for Broker Requests. It holds information about the current Broker making
 * requests and a Broker User Interface instance
 *
 * @author Edward Koulakidis
 */

public class BrokerRequests implements Handler {
    private Broker broker;

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
            case "add_new_accommodation" -> {
                AccommodationView accommodationView = new AccommodationView(new Accommodation(0, "", "", null, "", 0));
                accommodationView.setVisible(true);
                accommodationView.setAllFieldsEditable(true);
                accommodationView.getEditButton().setVisible(false);
                accommodationView.getDeleteButton().setVisible(false);
            }
            case "edit_accommodation" -> {

            }
            case "delete" -> {
                DatabaseAPI.reservationDatabase.dropAllReservationsByAccommodation(AccommodationView.accommodation);
                DatabaseAPI.reviewsDatabase.dropAllReviewsByAccommodation(AccommodationView.accommodation);
                DatabaseAPI.brokerAccommodationsDatabase.dropAccommodation((Broker)
                        Application.getInstance().getCurrentUser(), AccommodationView.accommodation);
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
