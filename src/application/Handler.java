package application;

import UI.*;
import accommodations.Accommodation;
import auth.Credentials;
import communication.Message;
import communication.Review;
import users.Broker;
import users.User;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

public class Handler {
    private User currentUser = null;
    public boolean quit;

    public boolean handleConnectionRequests(String request) throws NoSuchAlgorithmException {
        switch (request) {
            case "signin" -> {
                SignInUI signin = new SignInUI();
                signin.show();
                String[] request1 = signin.getRequest().split("\\s");
                Credentials credentials = new Credentials(request1[0], request1[1]);
                currentUser = DatabaseAPI.getCredentialsUserDatabase().selectUser(credentials);
                if (currentUser == null) {
                    UI.LOG(UIMessage.SIGN_IN_FAILED);
                    return false;
                } else {
                    UI.LOG(UIMessage.SIGN_IN_SUCCESS);
                    return true;
                }
            }
            case "signup" -> {
                SignUpUI signup = new SignUpUI();
                signup.show();
                currentUser = signup.getNewUser();
                if (DatabaseAPI.getCredentialsUserDatabase().selectUser(currentUser.getCredentials()) != null) { // duplicate credentials
                    UI.LOG(UIMessage.SIGN_UP_FAILED);
                    return false;
                } else {
                    UI.LOG(UIMessage.SIGN_UP_SUCCESS);
                    DatabaseAPI.getCredentialsUserDatabase().insertUser(currentUser.getCredentials(), currentUser);
                    DatabaseAPI.getUserConfirmationsDatabase().insertUserConfirmation(currentUser);
                    return true;
                }
            }
            case "quit" -> quit = true;
        }
        System.out.println("wtf");
        return false;
    }


    public void handleUserRequests() {
        switch (currentUser.getPrivilege()) {
            case CUSTOMER -> {
                CustomerUI customerUI = new CustomerUI(currentUser);
                customerUI.show();
                handleCustomerRequests(customerUI);
            }
            case BROKER -> {
                BrokerUI brokerUI = new BrokerUI();
                brokerUI.show();
                handleBrokerRequests(brokerUI);

            }
            case ADMIN -> {
                AdminUI adminUI = new AdminUI();
                adminUI.show();
                handleAdminRequests(adminUI);
            }
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    private void handleBrokerRequests(BrokerUI brokerUI) {
        System.out.println("Handling Broker requests:" + brokerUI.getRequest());
        switch (brokerUI.getRequest()) {
            case "view" -> {
                HashSet<Accommodation> accommodationList =
                        DatabaseAPI.getBrokerAccommodationsDatabase().selectAllAccommodationsFromBroker((Broker) currentUser);
                for (Accommodation accommodation : accommodationList) {
                    System.out.println(accommodation.toString());
                }

            }
            case "add" -> {
                Accommodation accommodation = brokerUI.addAccommodation();
                DatabaseAPI.getBrokerAccommodationsDatabase().insertAccommodation((Broker) currentUser, accommodation);
            }
            case "edit" -> {
                int id = Integer.parseInt(brokerUI.getInput("Enter the id of the Accommodation you wish to edit", "^[1-9][0-9]*$"));
                Accommodation accommodation = DatabaseAPI.getBrokerAccommodationsDatabase().selectAccommodationByID((Broker) currentUser, id);
                if (accommodation == null) {
                    UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                } else {
                    brokerUI.editAccommodation(accommodation);
                }
            }
            case "delete" -> {
                int id = Integer.parseInt(brokerUI.getInput("Enter the ID of the Accommodation you wish to delete", "^[1-9][0-9]*$"));
                Accommodation accommodation = DatabaseAPI.getBrokerAccommodationsDatabase().selectAccommodationByID((Broker) currentUser, id);
                if (accommodation == null) {
                    UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                } else {
                    DatabaseAPI.getBrokerAccommodationsDatabase().dropAccommodation((Broker) currentUser, accommodation);
                    //  accommodationReviewsDatabase.dropAllReviewsFromAccommodation(accommodation);
                    //add removeall reservations
                    UI.LOG(UIMessage.ENTRY_DELETED);
                }

            }
        }

    }

    private void handleCustomerRequests(CustomerUI customerUI) {
        System.out.println("Handling Customer requests: )" + customerUI.getRequest());
        switch (customerUI.getRequest()) {
            case "search" -> {
                //customerUI.searchAccommodations();
                System.out.println("search");

            }
            case "viewrev" -> {
                for (Review review : DatabaseAPI.getReviewsDatabase().selectReviewsByUser(currentUser)) {
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
                String temp = customerUI.getInput("Enter the id of the accommodation you wish to review", "^[1-9][0-9]*$");
                accommodation = DatabaseAPI.getBrokerAccommodationsDatabase().selectAccommodationByID(Integer.parseInt(temp));
                if (accommodation != null) {
                    Review review = customerUI.addReview(accommodation);
                    DatabaseAPI.getReviewsDatabase().insertReview(review);
                } else {
                    UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                }
            }

        }
    }

    private void handleAdminRequests(AdminUI adminUI) {
        System.out.println("Handling Admin requests:" + adminUI.getRequest());
        switch (adminUI.getRequest()) {
            case "confirm" -> {
                String confirmationMessage = "Hello reply to this email to confirm your account";
                for (User user : DatabaseAPI.getUserConfirmationsDatabase().selectAllUsersWhereConfirmedIs(false)) {
                    System.out.println("Send confirmation message to user " + user.toString() + "\ny/n");
                    char ans = adminUI.getInput("y/n", "y|Y|N|n").toLowerCase().charAt(0);
                    if (ans == 'y')
                        DatabaseAPI.getUserMessagesDatabase().insertMessageToUser(user, new Message(currentUser, user, confirmationMessage));
                }
            }
            case "stats" -> {
                System.out.println("Total number of users : " + DatabaseAPI.getUserConfirmationsDatabase().selectAllUsers().size());
                System.out.println("Total number of accommodations : " + DatabaseAPI.getBrokerAccommodationsDatabase().selectAllAccommodations().size());
            }

        }
    }


}
