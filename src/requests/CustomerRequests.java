package requests;

import UI.CustomerUI;
import UI.InboxUI;
import UI.UI;
import UI.UIMessage;
import accommodations.Accommodation;
import accommodations.Reservation;
import accommodations.TimePeriod;
import application.Application;
import application.DatabaseAPI;
import communication.Review;
import users.Customer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


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
        ui = new CustomerUI(customer);
        this.customer = customer;

    }

    /**
     * The main method for handling Customer requests. It handle requests for viewing/adding reservations and reviews,
     * searching accommodations and viewing messages
     */
    @Override
    public void handleRequests() {
        boolean quit = false;
        while (!quit) {
            ui.show();
            switch (ui.getRequest()) {
                case "search" -> //Search accommodaions
                        searchAccommodations();
                case "addres" -> {
                    //Make a reservation
                    Accommodation accommodation;
                    for (Accommodation accommodation1 : DatabaseAPI.brokerAccommodationsDatabase.selectAllAccommodations()) {
                        System.out.println(accommodation1.toString());
                    }
                    String temp = ui.getInput("Enter the id of the accommodation you wish to reserve", "^[1-9][0-9]*$");
                    accommodation = DatabaseAPI.brokerAccommodationsDatabase.selectAccommodationByID(Integer.parseInt(temp));
                    if (accommodation == null)
                        UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                    else {
                        System.out.println("Accommodation is reserved for the following time periods:\n");
                        DatabaseAPI.accommodationsCalendarDatabase.selectCalendarFromAccommodation(accommodation).
                                forEach(System.out::println);
                        System.out.println("At what date and time will you be arriving?");
                        LocalDateTime startDate = parseDate();
                        System.out.println("At what date and time will you be leaving?");
                        LocalDateTime endDate = parseDate();
                        TimePeriod timePeriod = new TimePeriod(startDate, endDate);
                        if (DatabaseAPI.accommodationsCalendarDatabase.accommodationAvailableInTimePeriod(accommodation, timePeriod)) {
                            String comments = ui.getInput("Add some comments if you like", "(?s).*");
                            Reservation reservation = new Reservation(customer, accommodation, timePeriod, comments);
                            DatabaseAPI.accommodationsCalendarDatabase.insertTimePeriodToAccommodation(accommodation, timePeriod);
                            DatabaseAPI.reservationDatabase.insertReservation(reservation);
                            System.out.println("Reservation made succesfully");
                        } else
                            System.out.println("The accommodation is already booked for the date specified.");
                        Application.sleepFor(2);
                    }
                }
                case "viewrev" -> {
                    //View all reviews by customer
                    List<Review> reviews = DatabaseAPI.reviewsDatabase.selectReviewsByUser(customer);
                    if (reviews.isEmpty())
                        System.out.println("You haven't made any reviews yet");
                    else
                        reviews.forEach(System.out::println);
                    Application.sleepFor(2);
                }
                case "viewres" -> {
                    //View all reservations by customer
                    ArrayList<Reservation> reservations = DatabaseAPI.reservationDatabase.selectReservationsByUser(customer);
                    if (reservations.isEmpty())
                        System.out.println("You haven't made any reservations yet");
                    else
                        reservations.forEach(System.out::println);
                    Application.sleepFor(2);
                }
                case "addrev" -> {
                    //Adds a new review to an Accommodation by ID
                    Accommodation accommodation;
                    for (Accommodation accommodation1 : DatabaseAPI.brokerAccommodationsDatabase.selectAllAccommodations()) {
                        System.out.println(accommodation1.toString());
                    }
                    String temp = ui.getInput("Enter the id of the accommodation you wish to review", "^[1-9][0-9]*$");
                    accommodation = DatabaseAPI.brokerAccommodationsDatabase.selectAccommodationByID(Integer.parseInt(temp));
                    if (accommodation != null) { //accommodation exists
                        AtomicBoolean unique = new AtomicBoolean(true);
                        DatabaseAPI.reviewsDatabase.selectReviewsByAccommodation(accommodation).forEach(review -> {
                            if (review.user() == customer) {
                                System.out.println("You already made a review in this accommodation");
                                unique.set(false);
                            }
                        });
                        if (unique.get()) {

                            Review review = ui.addReview(accommodation);
                            DatabaseAPI.reviewsDatabase.insertReview(review);
                            System.out.println("Review added succesfully");
                        }
                    } else {
                        UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                    }
                    Application.sleepFor(2);
                }
                case "inbox" -> {
                    InboxUI inbox = new InboxUI(customer);
                    inbox.show();
                }
                case "signout" -> quit = true;
            }
        }
    }

    /**
     * Method for parsing a Date. Since Java is a terrible languange its needed
     *
     * @return a LocalDateTime object by user input
     */
    private LocalDateTime parseDate() {

        String[] temp = ui.getInput("Insert date in form of dd-mm-yyyy",
                "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[13-9]|1[0-2])\\2))(?:(?:" +
                        "1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]" +
                        "|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:" +
                        "0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$").split("-");
        int day = Integer.parseInt(temp[0]);
        int month = Integer.parseInt(temp[1]);
        int year = Integer.parseInt(temp[2]);
        temp = ui.getInput("Insert a time in format HH:MM", "^((0[0-9]|1[0-9]|2[0-3]):[0-5][0-9])$").split(":");
        int hour = Integer.parseInt(temp[0]);
        int minute = Integer.parseInt(temp[1]);
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    /**
     * Method for searching accommodations with different filters
     */
    private void searchAccommodations() {
        System.out.println("Please enter the filters to search by city/price/space/both or all to view all Accommodations");
        String ans = ui.getInput("Enter price/space/both/city/all", "city|price|space|both|all");
        HashSet<Accommodation> found = new HashSet<Accommodation>();
        switch (ans) {
            case "all" -> found = DatabaseAPI.brokerAccommodationsDatabase.selectAllAccommodations();
            case "price" -> {
                int lowPrice = Integer.parseInt(ui.getInput("Enter the lower price limit", "\\d+"));
                int highPrice = Integer.parseInt(ui.getInput("Enter the upper price limit", "\\d+"));
                found = DatabaseAPI.brokerAccommodationsDatabase.selectAccommodationsByPrice(lowPrice, highPrice);
            }
            case "space" -> {
                int lowSpace = Integer.parseInt(ui.getInput("Enter the lower space limit", "\\d+"));
                int highSpace = Integer.parseInt(ui.getInput("Enter the upper space limit", "\\d+"));
                found = DatabaseAPI.brokerAccommodationsDatabase.selectAccommodationsBySpace(lowSpace, highSpace);
            }
            case "both" -> {
                int lowPrice = Integer.parseInt(ui.getInput("Enter the lower price limit", "\\d+"));
                int highPrice = Integer.parseInt(ui.getInput("Enter the upper price limit", "\\d+"));
                found = DatabaseAPI.brokerAccommodationsDatabase.selectAccommodationsByPrice(lowPrice, highPrice);
                int lowSpace = Integer.parseInt(ui.getInput("Enter the lower space limit", "\\d+"));
                int highSpace = Integer.parseInt(ui.getInput("Enter the upper space limit", "\\d+"));
                found.addAll(DatabaseAPI.brokerAccommodationsDatabase.selectAccommodationsBySpace(lowSpace, highSpace));
            }
            case "city" -> {
                String city = ui.getInput("Enter the city", "");
                found = DatabaseAPI.brokerAccommodationsDatabase.selectAccommodationsByCity(city);
            }
        }
        if (found.isEmpty())
            UI.LOG(UIMessage.ENTRY_NOT_FOUND);
        else
            found.forEach(System.out::println);
    }
}
