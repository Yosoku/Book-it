package UI;

import accommodations.Accommodation;
import application.Application;
import communication.Review;
import users.User;

public class CustomerUI extends UI {
    private User customer;

    public CustomerUI(User currentUser) {
        customer = currentUser;
        Application.sleepFor(1);
        System.out.println("Welcome back " + customer.getName() + ",how would you like to proceed?");
        Application.sleepFor(1);
    }

    @Override
    public void show() {
        System.out.println("\n---Options---");
        System.out.println("\n>View reservations(viewres)\n>View reviews(viewrev)\n>Search Accommodations(search)" +
                "\n>Make reservation(addres)\n>Make review(addrev)\n>Sign out(signout)");
        request = getInput("Enter viewres/viewrev/search/addres/addrev/signout",
                "(signout|search|viewrev|viewres|addrev|addres)");

    }


    public Review addReview(Accommodation accommodation){
        int stars = Integer.parseInt(getInput("How many stars does the accommodation get?","[1-5]"));
        String description = getInput("Write a small description for your review ","");
        return new Review(stars, profanityFilter(description), customer, accommodation);
    }


}
