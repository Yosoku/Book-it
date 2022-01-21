package backend.requests;

import backend.application.DatabaseAPI;
import backend.application.Server;
import backend.users.Customer;
import frontend.AccommodationUI.MakeReservationView;
import frontend.AppWindow;
import frontend.Inbox.InboxView;
import frontend.usersgui.CustomerHomePage;
import frontend.usersgui.ProfileView;

import javax.swing.*;


/**
 * A class representing a Request Handler for Customer Requests. It holds information about the current Customer making
 * requests and a Customer User Interface instance
 *
 * @author Edward Koulakidis
 */
public class CustomerRequests implements Handler {
    private Customer customer;

    /**
     * Initializer constructor
     *
     * @param customer The customer making requests
     */
    public CustomerRequests(Customer customer) {
        this.customer = customer;
        AppWindow.getInstance().changePanel(new CustomerHomePage(customer));
    }

    /**
     * The main method for handling Customer requests. It handle requests for viewing/adding reservations and reviews,
     * searching accommodations and viewing messages
     */
    @Override
    public void handleRequests() {

        switch (Server.getCurrentRequest()) {
            case "make_reservation" -> {
                DatabaseAPI.reservationDatabase.insertReservation(MakeReservationView.reservation);
                DatabaseAPI.accommodationsCalendarDatabase.insertTimePeriodToAccommodation(MakeReservationView.accommodation, MakeReservationView.reservation.period());
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
