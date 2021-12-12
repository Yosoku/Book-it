package application;

import UI.*;
import users.User;

import java.util.concurrent.TimeUnit;

/**
 * A class representing the main Application in Booking clone. The class uses a Server for handling requests and making
 * calls to the DatabaseAPI. It holds information about the currentUser using the Application.
 *
 * @author Edward Koulakidis
 * @see Server
 * @see requests.Handler
 * @see DatabaseAPI
 */
public class Application {
    private static boolean sleep;
    boolean isRunning;
    private Server requestHandler;
    private User currentUser;
    private ConnectUI connectUI;

    /**
     * Initializer Constructor to start the Server and the connect UI
     * @param sleep if true,the application will sleep between messages for higher quality user experience.
     * Its reccommended to turn off during debugging
     */
    public Application(boolean sleep) {
        requestHandler = new Server();
        this.sleep = sleep;
        connectUI = new ConnectUI();
        run();
    }


    /**
     * The main application loop. It makes an API call to Load the Database when run,and write the Database when exited
     * The server handles the rest
     */
    private void run() {
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