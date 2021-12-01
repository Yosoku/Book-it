package requests;

import UI.BrokerUI;
import UI.UI;
import UI.UIMessage;
import accommodations.Accommodation;
import application.DatabaseAPI;
import users.Broker;

import java.util.HashSet;

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
                    for (Accommodation accommodation : accommodationList) {
                        System.out.println(accommodation.toString());
                    }

                }
                case "add" -> {
                    Accommodation accommodation = ui.addAccommodation();
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
                        //  accommodationReviewsDatabase.dropAllReviewsFromAccommodation(accommodation);
                        //add removeall reservations
                        UI.LOG(UIMessage.ENTRY_DELETED);
                    }

                }
                case "signout" -> quit = true;
            }
        }
    }
}
