package application;

import UI.*;
import accommodations.Accommodation;
import auth.Credentials;
import communication.Message;
import communication.Review;
import database.*;
import users.*;

import java.security.NoSuchAlgorithmException;
import java.util.HashSet;


public class Application {

    boolean isRunning;
    private BrokerAccommodations brokerAccommodationsDatabase;
    private UserConfirmations userConfirmationsDatabase;
    private AccommodationReviews accommodationReviewsDatabase;
    private CredentialsUser credentialsUserDatabase;
    private CustomerReviews customerReviewsDatabase;
    private UserMessages userMessagesDatabase;
    private User currentUser;
    private ConnectUI connectUI;

    public Application() throws NoSuchAlgorithmException {
        initDatabases();
        connectUI = new ConnectUI();
        loadData();
        run();
        writeData();

    }

    private void initAdmin() throws NoSuchAlgorithmException {
        Admin admin = new Admin(new Credentials("Edward", "password"),
                "Edward", 23, "edouardos@csd.auth.gr", Gender.MALE, "6982093778");
        if (credentialsUserDatabase.selectUser(admin.getCredentials()) == null) { //entry not found
            userConfirmationsDatabase.insertUserConfirmation(admin);
            userConfirmationsDatabase.updateUserConfirmation(admin);
            credentialsUserDatabase.insertUser(admin.getCredentials(), admin);
        }
        Broker broker = new Broker(new Credentials("broker", "broker"), "broker", 23, "broker@csd.gr", Gender.MALE, "12", "ads");
        Accommodation ac = new Accommodation(19, "", null, "", 2, true);
        brokerAccommodationsDatabase.insertAccommodation(broker, ac);
    }


    private void run() throws NoSuchAlgorithmException {
        isRunning = true;
        System.out.println("Welcome UI");
        while (isRunning) {
            connectUI.show();
            if (handleConnections(connectUI.getRequest())) {
                //connection established
                userConfirmationsDatabase.updateUserConfirmation(currentUser); // DELETE LATER
                if (userConfirmationsDatabase.selectUserConfirmation(currentUser)) {
                    //User confirmed show options
                    handleUserRequests();
                } else {
                    UI.LOG(UIMessage.CONFIRMATION_FAILED);
                    InboxUI inboxUI = new InboxUI(userMessagesDatabase.selectMessageFromUser(currentUser));
                    inboxUI.show();
                }
            }
        }
        System.out.println("Credits");
    }

    private void handleUserRequests() {
        switch (currentUser.getPrivilege()) {
            case CUSTOMER -> {
                CustomerUI customerUI = new CustomerUI();
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

    private void handleBrokerRequests(BrokerUI brokerUI) {
        System.out.println("Handling Broker requests:" + brokerUI.getRequest());
        switch (brokerUI.getRequest()) {
            case "view" -> {
                HashSet<Accommodation> accommodationList =
                        brokerAccommodationsDatabase.selectAllAccommodationsFromBroker((Broker) currentUser);
                for (Accommodation accommodation : accommodationList) {
                    System.out.println(accommodation.toString());
                }

            }
            case "add" -> {
                Accommodation accommodation = brokerUI.addAccommodation();
                brokerAccommodationsDatabase.insertAccommodation((Broker) currentUser, accommodation);
            }
            case "edit" -> {
                int id = Integer.parseInt(brokerUI.getInput("Enter the id of the Accommodation you wish to edit", "^[1-9][0-9]*$"));
                Accommodation accommodation = brokerAccommodationsDatabase.selectAccommodationByID((Broker) currentUser, id);
                if (accommodation == null) {
                    UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                } else {
                    brokerUI.editAccommodation(accommodation);
                }
            }
            case "delete" -> {
                int id = Integer.parseInt(brokerUI.getInput("Enter the ID of the Accommodation you wish to delete", "^[1-9][0-9]*$"));
                Accommodation accommodation = brokerAccommodationsDatabase.selectAccommodationByID((Broker) currentUser, id);
                if (accommodation == null) {
                    UI.LOG(UIMessage.ENTRY_NOT_FOUND);
                } else {
                    brokerAccommodationsDatabase.dropAccommodation((Broker) currentUser, accommodation);
                    accommodationReviewsDatabase.dropAllReviewsFromAccommodation(accommodation);
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
                customerUI.searchAccommodations();
                System.out.println("search");

            }
            case "viewrev" -> {
                for (Review review : customerReviewsDatabase.selectReviewFromUser((Customer) currentUser)) {
                    System.out.println(review.toString());
                }

            }
            case "viewres" -> {

            }
            case "addrev" -> {
                Accommodation accommodation;
                for (Accommodation accommodation1 : brokerAccommodationsDatabase.selectAllAccommodations()) {
                    System.out.println(accommodation1.toString());
                }
                String temp = customerUI.getInput("Enter the id of the accommodation you wish to review", "^[1-9][0-9]*$");
                accommodation = brokerAccommodationsDatabase.selectAccommodationByID(Integer.parseInt(temp));
                if (accommodation != null) {
                    Review review = customerUI.addReview(accommodation);
                    customerReviewsDatabase.insertReviewToUser((Customer) currentUser, review);
                    accommodationReviewsDatabase.insertReviewToAccommodation(accommodation, review);
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
                for (User user : userConfirmationsDatabase.selectAllUsersWhereConfirmedIs(false)) {
                    System.out.println("Send confirmation message to user " + user.toString() + "\ny/n");
                    char ans = adminUI.getInput("y/n", "y|Y|N|n").toLowerCase().charAt(0);
                    if (ans == 'y')
                        userMessagesDatabase.insertMessageToUser(user, new Message(currentUser, user, confirmationMessage));
                }
            }
            case "stats" -> {
                System.out.println("Total number of users : " + userConfirmationsDatabase.selectAllUsers().size());
                System.out.println("Total number of accommodations : " + brokerAccommodationsDatabase.selectAllAccommodations().size());
            }

        }
    }

    private boolean handleConnections(String request) throws NoSuchAlgorithmException {
        switch (request) {
            case "signin" -> {
                SignInUI signin = new SignInUI();
                signin.show();
                String[] request1 = signin.getRequest().split("\\s");
                Credentials credentials = new Credentials(request1[0], request1[1]);
                currentUser = credentialsUserDatabase.selectUser(credentials);
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
                if (credentialsUserDatabase.selectUser(currentUser.getCredentials()) != null) { // duplicate credentials
                    UI.LOG(UIMessage.SIGN_UP_FAILED);
                    return false;
                } else {
                    UI.LOG(UIMessage.SIGN_UP_SUCCESS);
                    credentialsUserDatabase.insertUser(currentUser.getCredentials(), currentUser);
                    userConfirmationsDatabase.insertUserConfirmation(currentUser);
                    return true;
                }
            }
            case "quit" -> isRunning = false;
        }
        System.out.println("wtf");
        return false;

    }

    private void initDatabases() {

        accommodationReviewsDatabase = new AccommodationReviews();
        brokerAccommodationsDatabase = new BrokerAccommodations();
        credentialsUserDatabase = new CredentialsUser();
        customerReviewsDatabase = new CustomerReviews();
        userConfirmationsDatabase = new UserConfirmations();
        userMessagesDatabase = new UserMessages();

    }


    private void loadData() throws NoSuchAlgorithmException {

        //null in case of EOF
        Object temp = accommodationReviewsDatabase.read();
        if (temp != null)
            accommodationReviewsDatabase = (AccommodationReviews) temp;
        temp = brokerAccommodationsDatabase.read();
        if (temp != null)
            brokerAccommodationsDatabase = (BrokerAccommodations) temp;
        temp = credentialsUserDatabase.read();
        if (temp != null)
            credentialsUserDatabase = (CredentialsUser) temp;
        temp = customerReviewsDatabase.read();
        if (temp != null)
            customerReviewsDatabase = (CustomerReviews) temp;
        temp = userConfirmationsDatabase.read();
        if (temp != null)
            userConfirmationsDatabase = (UserConfirmations) temp;
        temp = userMessagesDatabase.read();
        if (temp != null)
            userMessagesDatabase = (UserMessages) temp;
        initAdmin();

    }

    private void writeData() {
        accommodationReviewsDatabase.write();
        brokerAccommodationsDatabase.write();
        credentialsUserDatabase.write();
        customerReviewsDatabase.write();
        userConfirmationsDatabase.write();
        userMessagesDatabase.write();
    }

}