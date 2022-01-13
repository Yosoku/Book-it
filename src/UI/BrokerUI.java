package UI;

import accommodations.Accommodation;
import users.User;

/**
 * A User Interface class shown to Users with Broker privileges
 *
 * @author Fwteinos Wawaroutas
 */
@Deprecated
public class BrokerUI extends UI {
    private User broker;


    /**
     * Initializer constructor
     * @param broker The current broker
     */
    public BrokerUI(User broker) {
        this.broker = broker;

        System.out.println("Welcome back " + broker.getName() + ",how would you like to proceed?");

    }

    /**
     * Method that prints all available options to the current User
     */
    @Override
    public void show() {
        System.out.println("\n---Options---");
        System.out.println("\n>View all Accommodations(view)\n>Add new Accommodation(add)\n" +
                ">Edit already existing Accommodation(edit)\n>Delete already existing Accommodation(delete)\n" +
                ">View all reservations(viewres)\n>View Inbox\n>Sign out(signout)");
        request = getInput("Enter view/add/edit/delete/viewres/inbox/signout", "(inbox|view|add|edit|delete|signout|viewres)");

    }

    /**
     * Method to Enter a new Accommodation by parsing user input
     * @return a new accommodation
     */
    public Accommodation addAccommodation() {
        int sqm = Integer.parseInt(getInput("How many square meters is the Accommodation you wish to add?", "^[1-9][0-9]*$"));
        String city = getInput("In which city is the Accommodation located", "^[a-zA-Z]+(?:[\\s-][a-zA-Z]+)*$");
        String address = getInput("What is the exact address of the accommodation?", ".+");
        int price = Integer.parseInt(getInput("What is the price in Euros per night?", "^[1-9][0-9]*$"));
        String description = getInput("Add a small description for your accommodation!", ".+");
        return new Accommodation(sqm, city, address, null, description, price);
    }

    /**
     * Method to Edit an existing Accommodation by parsing user input
     */
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
        ans = getInput("Would you like to add images? y/n", "y|Y|n|N").toLowerCase().charAt(0);
        if (ans == 'y') {
            //addImages();
            UI.LOG(UIMessage.FEATURE_UNAVAILABLE);
        }
    }
}
