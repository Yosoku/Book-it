package application;

import UI.ConnectUI;
import UI.InboxUI;
import UI.UI;
import UI.UIMessage;
import users.User;

import java.security.NoSuchAlgorithmException;


public class Application {
    boolean isRunning;
    DatabaseAPI databaseAPI;
    private Handler requestHandler;
    private User currentUser;
    private ConnectUI connectUI;

    public Application() throws NoSuchAlgorithmException {
        databaseAPI = new DatabaseAPI();
        requestHandler = new Handler();
        connectUI = new ConnectUI();
        run();
    }


    private void run() throws NoSuchAlgorithmException {
        DatabaseAPI.loadData();
        isRunning = true;
        System.out.println("Welcome UI");
        while (isRunning) {
            connectUI.show();
            if (requestHandler.handleConnectionRequests(connectUI.getRequest())) {
                //connection established
                currentUser = requestHandler.getCurrentUser();
                DatabaseAPI.getUserConfirmationsDatabase().updateUserConfirmation(currentUser); // DELETE LATER
                if (DatabaseAPI.getUserConfirmationsDatabase().selectUserConfirmation(currentUser)) {
                    //User confirmed show options
                    requestHandler.handleUserRequests();

                } else {
                    UI.LOG(UIMessage.CONFIRMATION_FAILED);
                    InboxUI inboxUI = new InboxUI(DatabaseAPI.getUserMessagesDatabase().selectMessageFromUser(currentUser));
                    inboxUI.show();
                }
            }
            isRunning = !requestHandler.quit;
        }
        DatabaseAPI.writeData();
        System.out.println("Credits");
    }

}