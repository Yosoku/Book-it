package requests;

import UI.BrokerUI;
import UI.UI;
import UI.UIMessage;
import accommodations.Accommodation;
import application.DatabaseAPI;
import communication.Message;
import users.Broker;
import users.User;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class BrokerRequests implements Handler {
    private Broker broker;
    private BrokerUI ui;

    public BrokerRequests(Broker broker) {
        ui = new BrokerUI(broker);
        this.broker = broker;
    }

    @Override
    public void handleRequests() {
        boolean quit = false;
        while (!quit) {
            ui.show();
            switch (ui.getRequest()) {
                case "view" -> {
                    HashSet<Accommodation> accommodationList =
                            DatabaseAPI.getBrokerAccommodationsDatabase().selectAllAccommodationsFromBroker(broker);
                    if (accommodationList == null) {
                        System.out.println("You have not added any accommodations yet.");
                    } else {
                        for (Accommodation accommodation : accommodationList) {
                            System.out.println(accommodation.toString());
                        }
                    }

                }
                case "add" -> {
                    Accommodation accommodation = ui.addAccommodation();
                    if (accommodation == null)
                        System.out.println("Invalid Accommodation");
                    else
                        DatabaseAPI.getBrokerAccommodationsDatabase().insertAccommodation(broker, accommodation);
                }
                case "edit" -> {
                    int id = Integer.parseInt(ui.getInput("Enter the id of the Accommodation you wish to edit", "^[1-9][0-9]*$"));
                    Accommodation accommodation = DatabaseAPI.getBrokerAccommodationsDatabase().selectAccommodationByID(broker, id);
                    if (accommodation == null) {
                        UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                    } else {
                        ui.editAccommodation(accommodation);
                    }
                }
                case "delete" -> {
                    int id = Integer.parseInt(ui.getInput("Enter the ID of the Accommodation you wish to delete", "^[1-9][0-9]*$"));
                    Accommodation accommodation = DatabaseAPI.getBrokerAccommodationsDatabase().selectAccommodationByID(broker, id);
                    if (accommodation == null) {
                        UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                    } else {
                        DatabaseAPI.getBrokerAccommodationsDatabase().dropAccommodation(broker, accommodation);
                        List<User> affectedUsers = new LinkedList<User>();
                        DatabaseAPI.getReviewsDatabase().selectReviewsByAccommodation(accommodation).forEach(review -> {
                            affectedUsers.add(review.user());
                        });
                        DatabaseAPI.getReservationsDatabase().selectReservationsByAccommodation(accommodation).forEach(reservation -> {
                            affectedUsers.add(reservation.user());
                        });
                        affectedUsers.forEach(user -> DatabaseAPI.getUserMessagesDatabase().insertMessageToUser(user,
                                new Message(broker, user, "Deleted Accommodation", "I regret to inform you that" +
                                        "the Accommodation" + accommodation.toString() + "was removed so any reservations/reviews" +
                                        "you had associated with it will be removed as well.")));
                        DatabaseAPI.getReservationsDatabase().dropAllReservationsByAccommodation(accommodation);
                        DatabaseAPI.getReviewsDatabase().dropAllReviewsByAccommodation(accommodation);
                        UI.LOG(UIMessage.ENTRY_DELETED);
                    }

                }
                case "signout" -> quit = true;
            }
        }
    }
}
