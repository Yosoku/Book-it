package requests;

import UI.CustomerUI;
import UI.UI;
import UI.UIMessage;
import accommodations.Accommodation;
import accommodations.Reservation;
import application.DatabaseAPI;
import communication.Review;
import users.Customer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
                case "search" -> //Search accommodaions
                        searchAccommodations();
                case "viewrev" -> {
                    //View all reviews by customer
                    List<Review> reviews = DatabaseAPI.getReviewsDatabase().selectReviewsByUser(customer);
                    if (reviews.isEmpty())
                        System.out.println("You haven't made any reviews yet");
                    else {
                        reviews.forEach(review -> System.out.println(review.toString()));
                    }
                }
                case "viewres" -> {
                    //View all reservations by customer
                    ArrayList<Reservation> reservations = DatabaseAPI.getReservationsDatabase().selectReservationsByUser(customer);
                    if (reservations.isEmpty())
                        System.out.println("You haven't made any reservations yet");
                    else {
                        reservations.forEach(reservation -> System.out.println(reservation.toString()));
                    }
                }
                case "addrev" -> {
                    //Adds a new review to an Accommodation by ID
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

    private void searchAccommodations() {
        System.out.println("Please enter the filters to search by price/space/both or all to view all Accommodations");
        String ans = ui.getInput("Enter price/space/both/all", "price|space|both|all");
        HashSet<Accommodation> found = new HashSet<Accommodation>();
        switch (ans) {
            case "all" -> found = DatabaseAPI.getBrokerAccommodationsDatabase().selectAllAccommodations();
            case "price" -> {
                int lowPrice = Integer.parseInt(ui.getInput("Enter the lower price limit", "\\d+"));
                int highPrice = Integer.parseInt(ui.getInput("Enter the upper price limit", "\\d+"));
                found = DatabaseAPI.getBrokerAccommodationsDatabase().selectAccommodationsByPrice(lowPrice, highPrice);
            }
            case "space" -> {
                int lowSpace = Integer.parseInt(ui.getInput("Enter the lower space limit", "\\d+"));
                int highSpace = Integer.parseInt(ui.getInput("Enter the upper space limit", "\\d+"));
                found = DatabaseAPI.getBrokerAccommodationsDatabase().selectAccommodationsBySpace(lowSpace, highSpace);
            }
            case "both" -> {
                int lowPrice = Integer.parseInt(ui.getInput("Enter the lower price limit", "\\d+"));
                int highPrice = Integer.parseInt(ui.getInput("Enter the upper price limit", "\\d+"));
                found = DatabaseAPI.getBrokerAccommodationsDatabase().selectAccommodationsByPrice(lowPrice, highPrice);
                int lowSpace = Integer.parseInt(ui.getInput("Enter the lower space limit", "\\d+"));
                int highSpace = Integer.parseInt(ui.getInput("Enter the upper space limit", "\\d+"));
                found.addAll(DatabaseAPI.getBrokerAccommodationsDatabase().selectAccommodationsBySpace(lowSpace, highSpace));
            }

        }
        if (found.isEmpty())
            UI.LOG(UIMessage.ENTRY_NOT_FOUND);
        else
            found.forEach(System.out::println);

    }
}
