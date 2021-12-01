package UI;

import accommodations.Accommodation;
import communication.Review;
import users.User;

public class CustomerUI extends UI {
    private User customer;

    public CustomerUI(User currentUser) {
        customer = currentUser;
        System.out.println("Welcome back " + customer.getName() + ",how would you like to proceed?");
    }

    @Override
    public void show() {
        System.out.println("\nView reservations(viewres)\nView reviews(viewrev)\nSearch Accommodations(search)" +
                "\nSign out(signout)");
        request = getInput("Enter viewres/viewrev/search/signout", "(signout|search|viewrev|viewres)");

    }


    public Review addReview(Accommodation accommodation){
        int stars = Integer.parseInt(getInput("How many stars does the accommodation get?","[1-5]"));
        String description = getInput("Write a small description for your review ","");
        return new Review(stars, profanityFilter(description), customer, accommodation);
    }


}
