package UI;

import accommodations.Accommodation;
import application.Application;
import communication.Review;
import users.User;
/**
 * A User Interface class shown to Users with Broker privileges
 *
 * @author Fwteinos Wawaroutas
 */
public class CustomerUI extends UI {
    private User customer;

    /**
     * Initializer constructor
     *
     * @param customer The current Customer
     */
    public CustomerUI(User customer) {
        customer = customer;
        Application.sleepFor(1);
        System.out.println("Welcome back " + customer.getName() + ",how would you like to proceed?");
        Application.sleepFor(1);
    }

    /**
     * Method that prints all available options to the current User
     */
    @Override
    public void show() {
        System.out.println("\n---Options---");
        System.out.println("\n>View reservations(viewres)\n>View reviews(viewrev)\n>Search Accommodations(search)" +
                "\n>Make reservation(addres)\n>Make review(addrev)\n>Sign out(signout)");
        request = getInput("Enter viewres/viewrev/search/addres/addrev/signout",
                "(signout|search|viewrev|viewres|addrev|addres)");

    }

    /**
     * Method to write a Review in an Accommodation
     *
     * @param accommodation accommodation to write a review on
     */
    public Review addReview(Accommodation accommodation) {
        int stars = Integer.parseInt(getInput("How many stars does the accommodation get?", "[1-5]"));
        String description = getInput("Write a small description for your review ", "");
        return new Review(stars, profanityFilter(description), customer, accommodation);
    }


}
