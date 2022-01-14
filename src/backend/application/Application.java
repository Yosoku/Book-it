package backend.application;

import backend.users.User;
import frontend.AppWindow;

import javax.swing.*;

/**
 * A class representing the main Application in Booking clone. The class uses a Server for handling requests and making
 * calls to the DatabaseAPI. It holds information about the currentUser using the Application.
 *
 * @author Edward Koulakidis
 * @see Server
 * @see backend.requests.Handler
 * @see DatabaseAPI
 */
public class Application {

    private static Application instance;
    boolean isRunning;
    boolean connected;
    private Server requestHandler;
    private User currentUser;

    /**
     * Initializer Constructor to start the Server and the connect UI
     */
    private Application() {
        requestHandler = new Server();
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started Loading Databases");
                DatabaseAPI.loadData();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished loading Databases");
            }
        }.execute();
        AppWindow.getInstance();
        connected = false;
    }

    public static Application getInstance() {
        if (instance == null)
            instance = new Application();
        return instance;
    }

    /**
     * The main application loop. It makes an API call to Load the Database when run,and write the Database when exited
     * The server handles the rest
     */
    public synchronized void run() {
        isRunning = true;
        //wait for login
        while (isRunning) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (requestHandler.handleConnectionRequests()) {
                System.out.println("Connected");
                connected = true;
                currentUser = requestHandler.getCurrentUser();
                while (connected) {
                    requestHandler.handleUserRequests();
                    try {
                        if (connected)
                            wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else
                System.out.println("Can't connect");
        }
    }


    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public synchronized void notifyThread() {
        notify();
    }
}