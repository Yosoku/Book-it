package application;

import UI.*;
import users.User;

import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeUnit;


public class Application {
    private static boolean sleep;
    boolean isRunning;
    private Server requestHandler;
    private User currentUser;
    private ConnectUI connectUI;

    public Application(boolean sleep) throws NoSuchAlgorithmException {
        requestHandler = new Server();
        this.sleep = sleep;
        connectUI = new ConnectUI();
        run();
    }


    private void run() throws NoSuchAlgorithmException {
        DatabaseAPI.loadData();
        isRunning = true;
        new WelcomeUI().show();
        while (isRunning) {
            connectUI.show();
            isRunning = !connectUI.getRequest().equals("quit");
            if (requestHandler.handleConnectionRequests(connectUI.getRequest())) {
                //connection established
                currentUser = requestHandler.getCurrentUser();
                if (DatabaseAPI.userConfirmationsDatabase.selectUserConfirmation(currentUser)) {
                    //User confirmed show options
                    requestHandler.handleUserRequests();
                } else {
                    UI.LOG(UIMessage.CONFIRMATION_FAILED);
                    InboxUI inboxUI = new InboxUI(currentUser);
                    inboxUI.show();
                }
            }

        }
        DatabaseAPI.writeData();
        System.out.println("Credits");
    }

    /**
     * A method for sleeping/pausing running of the application. Useful in CLI for high quality user experience. A static
     * boolean is used to avoid sleeping when debugging
     *
     * @param seconds How many seconds to sleep for
     */
    public static void sleepFor(int seconds) {
        if (!sleep)
            return;
        else
            try {
                TimeUnit.SECONDS.sleep(seconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}