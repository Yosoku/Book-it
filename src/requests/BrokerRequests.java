package requests;

import UI.BrokerUI;
import UI.UI;
import UI.UIMessage;
import accommodations.Accommodation;
import accommodations.Reservation;
import application.Application;
import application.DatabaseAPI;
import communication.Message;
import users.Broker;
import users.User;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

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
        ui = new BrokerUI(broker);
        this.broker = broker;
    }

    /**
     * The main method for handling Broker requests. It handles requests for viewing all accommodations,reservations
     * Adding/removing/edtting accommodations
     */
    @Override
    public void handleRequests() {
        boolean quit = false;
        while (!quit) {
            ui.show();
            switch (ui.getRequest()) {
                case "view" -> {
                    HashSet<Accommodation> accommodationList =
                            DatabaseAPI.brokerAccommodationsDatabase.selectAllAccommodationsFromBroker(broker);
                    if (accommodationList.isEmpty())
                        System.out.println("You have not added any accommodations yet.");
                    else
                        accommodationList.forEach(System.out::println);
                    Application.sleepFor(2);
                }
                case "add" -> {
                    Accommodation accommodation = ui.addAccommodation();
                    if (accommodation == null)
                        System.out.println("Invalid Accommodation");
                    else
                        DatabaseAPI.brokerAccommodationsDatabase.insertAccommodation(broker, accommodation);
                    System.out.println("A new Accommodation has been added succesfully");
                }
                case "edit" -> {
                    int id = Integer.parseInt(ui.getInput("Enter the id of the Accommodation you wish to edit", "^[1-9][0-9]*$"));
                    Accommodation accommodation = DatabaseAPI.brokerAccommodationsDatabase.selectAccommodationByID(broker, id);
                    if (accommodation == null) {
                        UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                    } else {
                        ui.editAccommodation(accommodation);
                    }
                }
                case "delete" -> {
                    int id = Integer.parseInt(ui.getInput("Enter the ID of the Accommodation you wish to delete", "^[1-9][0-9]*$"));
                    Accommodation accommodation = DatabaseAPI.brokerAccommodationsDatabase.selectAccommodationByID(broker, id);
                    if (accommodation == null) {
                        UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                    } else {
                        DatabaseAPI.brokerAccommodationsDatabase.dropAccommodation(broker, accommodation);
                        List<User> affectedUsers = new LinkedList<User>();
                        DatabaseAPI.reviewsDatabase.selectReviewsByAccommodation(accommodation).forEach(review -> affectedUsers.add(review.user()));
                        DatabaseAPI.reservationDatabase.selectReservationsByAccommodation(accommodation).forEach(reservation -> affectedUsers.add(reservation.user()));
                        affectedUsers.forEach(user -> DatabaseAPI.userMessagesDatabase.insertMessageToUser(user,
                                new Message(broker, user, "Deleted Accommodation", "I regret to inform you that" +
                                        "the Accommodation" + accommodation + "was removed so any reservations/reviews" +
                                        "you had associated with it will be removed as well.")));
                        DatabaseAPI.reservationDatabase.dropAllReservationsByAccommodation(accommodation);
                        DatabaseAPI.reviewsDatabase.dropAllReviewsByAccommodation(accommodation);
                        UI.LOG(UIMessage.ENTRY_DELETED);
                    }

                }
                case "viewres" -> {
                    HashSet<Reservation> reservations = DatabaseAPI.reservationDatabase.selectAllReservationsByBroker(broker);
                    if (reservations.isEmpty())
                        System.out.println("Your Accommodations have no active Reservations");
                    else
                        reservations.forEach(System.out::println);
                    Application.sleepFor(2);
                }

                case "signout" -> quit = true;
            }
        }
    }
}
