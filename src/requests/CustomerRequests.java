package requests;

import UI.CustomerUI;
import UI.UI;
import UI.UIMessage;
import accommodations.Accommodation;
import application.DatabaseAPI;
import communication.Review;
import users.Customer;

public class CustomerRequests implements Handler {
    private CustomerUI ui;
    private Customer customer;

    public CustomerRequests(Customer customer) {
        ui = new CustomerUI(customer);
        this.customer = customer;

    }

    @Override
    public void handleRequests() {
        boolean quit = false;
        while (!quit) {
            ui.show();
            switch (ui.getRequest()) {
                case "search" -> {
                    //customerUI.searchAccommodations();
                    System.out.println("search");

                }
                case "viewrev" -> {
                    for (Review review : DatabaseAPI.getReviewsDatabase().selectReviewsByUser(customer)) {
                        System.out.println(review.toString());
                    }

                }
                case "viewres" -> {

                }
                case "addrev" -> {
                    Accommodation accommodation;
                    for (Accommodation accommodation1 : DatabaseAPI.getBrokerAccommodationsDatabase().selectAllAccommodations()) {
                        System.out.println(accommodation1.toString());
                    }
                    String temp = ui.getInput("Enter the id of the accommodation you wish to review", "^[1-9][0-9]*$");
                    accommodation = DatabaseAPI.getBrokerAccommodationsDatabase().selectAccommodationByID(Integer.parseInt(temp));
                    if (accommodation != null) {
                        Review review = ui.addReview(accommodation);
                        DatabaseAPI.getReviewsDatabase().insertReview(review);
                    } else {
                        UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                    }
                }
                case "signout" -> quit = true;
            }
        }
    }
}
