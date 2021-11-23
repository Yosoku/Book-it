package UI;

import accommodations.Accommodation;
import communication.Review;

public class CustomerUI extends UI{
    @Override
    public void show() {
        System.out.println("Welcome back User,how would you like to proceed?\nView reservations(viewres)\n" +
                "View reviews(viewrev)\nSearch Accommodations(search)");
        request = getInput("Enter viewres/viewrev/search","(search|viewrev|viewres)");

    }


    public Review addReview(Accommodation accommodation){
        int stars = Integer.parseInt(getInput("How many stars does the accommodation get?","[1-5]"));
        String description = getInput("Write a small description for your review ","");
        return new Review(stars,profanityFilter(description));
    }

    public void searchAccommodations() {
    }
}
