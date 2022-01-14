package backend.requests;

import backend.UI.CustomerUI;
import backend.application.DatabaseAPI;
import backend.application.Server;
import backend.users.Customer;
import frontend.AccommodationUI.MakeReservationView;
import frontend.AppWindow;
import frontend.Inbox.InboxView;
import frontend.ProfileView;
import frontend.usersgui.CustomerHomePage;

import javax.swing.*;


/**
 * A class representing a Request Handler for Customer Requests. It holds information about the current Customer making
 * requests and a Customer User Interface instance
 *
 * @author Edward Koulakidis
 */
public class CustomerRequests implements Handler {
    private CustomerUI ui;
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
            case "profile" -> {
                SwingUtilities.invokeLater(() -> {
                    JFrame profileView = new ProfileView(customer);
                    profileView.setVisible(true);
                });

            }
            case "inbox" -> {
                SwingUtilities.invokeLater(() -> {
                    InboxView inboxView = new InboxView(DatabaseAPI.userMessagesDatabase.selectMessageFromUser(customer));
                    inboxView.setVisible(true);
                });

            }
            case "signout" -> SwingUtilities.invokeLater(() -> AppWindow.getInstance().signOut());

        }

    }



    /**
     * Method for searching accommodations with different filters
     */
    private void searchAccommodations() {


    }
}
