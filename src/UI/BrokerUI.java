package UI;

import accommodations.Accommodation;
import communication.Review;

public class BrokerUI extends UI {

    @Override
    public void show() {
        System.out.println("Welcome back User,how would you like to proceed?\nAdd new Accommodation(add)\n" +
                "Edit already existing Accommodation(edit)\nDelete already existing Accommodation(delete)");
        request = getInput("Enter add/edit/delete", "(add|edit|delete|view)");
    }

    public Accommodation addAccommodation() {
        int sqm = Integer.parseInt(getInput("How many square meters is the Accommodation you wish to add?", "^[1-9][0-9]*$"));
        String address = getInput("What is the exact address of the accommodation?", "");
        int price = Integer.parseInt(getInput("What is the price in Euros per night?", "^[1-9][0-9]*$"));
        String description = getInput("Add a small description for your accommodation!", "");
        boolean available = Boolean.parseBoolean(getInput("Is it available for rent? You can change this later", "true|false"));
        return new Accommodation(sqm, address, null, description, price, available);
    }

    public void editAccommodation(Accommodation accommodation) {
        char ans;
        ans = getInput("Would you like to edit the square meters of the accommodation? y/n",
                "(y|n|Y|N)").toLowerCase().charAt(0);
        if (ans == 'y')
            accommodation.setSpace(Integer.parseInt(getInput("Add new square meters", "^[1-9][0-9]*$")));

        ans = getInput("Would you like to edit the address of the accommodation? y/n",
                "(y|n|Y|N)").toLowerCase().charAt(0);
        if (ans == 'y')
            accommodation.setAddress(getInput("Add new address", ""));

        ans = getInput("Would you like to edit the price per night of the accommodation? y/n",
                "(y|n|Y|N)").toLowerCase().charAt(0);
        if (ans == 'y')
            accommodation.setPrice(Integer.parseInt(getInput("Add new price per night", "^[1-9][0-9]*$")));

        ans = getInput("Would you like to edit the description of the accommodation? y/n",
                "(y|n|Y|N)").toLowerCase().charAt(0);
        if (ans == 'y')
            accommodation.setDescription(getInput("Add new description", ""));

        ans = getInput("Would you like to edit the availability of the accommodation? y/n",
                "(y|n|Y|N)").toLowerCase().charAt(0);
        if (ans == 'y')
            accommodation.setAvailable(Boolean.parseBoolean(getInput("Add new availability true/false", "true|false")));

        ans = getInput("Would you like to add images? y/n", "y|Y|n|N").toLowerCase().charAt(0);
        if (ans == 'y') {
            //addImages();
        }
    }
}
