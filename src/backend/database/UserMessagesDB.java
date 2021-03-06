package backend.database;

import backend.communication.Message;
import backend.users.User;

import javax.swing.*;
import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * <p>
 * The UserMessages is a class intended to handle information about messages in an application. It is implemented by a
 * Hashtable holding Users->ArrayList of Messages. It basically holds information about a Users inbox</p>
 * <p>
 * The UserMessages inherits from Database inheriting read/write methods and implementing Serializable interface. It also
 * provides basic queries like SELECT,INSERT and DROP. It is also important to note that <b>null</b> values are not stored
 * in this class</p>
 *
 * @author Edward Koulakidis
 * @see java.io.Serializable
 * @see Database
 */

public class UserMessagesDB extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private final HashMap<User, ArrayList<Message>> userInbox;

    /**
     * * An initializer constructor used to initialize the map and call the Base class with the specified filename to store
     * the map
     */
    public UserMessagesDB() {
        super("src/config/userMessages.ser");
        userInbox = new HashMap<User, ArrayList<Message>>();
    }


    /**
     * A query for inserting new Messages in the ArrayList of a User. If any of the parameters is <b>null</b> the method will exit
     * without changing the Hashmap. If message is the first entry this method also initilizes the ArrayList
     * Runs in O(1) time
     *
     * @param user    The user that received the message
     * @param message The message instance
     */
    public void insertMessageToUser(User user, Message message, boolean write) {
        if (user == null || message == null)
            return;
        ArrayList<Message> messagesList = userInbox.get(user);
        if (messagesList == null)
            messagesList = new ArrayList<>();
        messagesList.add(message);
        userInbox.put(user, messagesList);
        if (write)
            new SwingWorker<>() {
                @Override
                protected Object doInBackground() {
                    System.out.println("Started writing " + this);
                    write();
                    return null;
                }

                @Override
                protected void done() {
                    System.out.println("Finished writing Database");
                }
            }.execute();
    }

    /**
     * A query for selecting all Messages based on their receiver(User). If the user has no messages it returns null
     * Runs in O(1) time
     *
     * @param user The user to query messages from
     * @return The List of Messages mapped to the parameter user,if present in the map, null otherwise
     */
    public ArrayList<Message> selectMessageFromUser(User user) {
        return userInbox.get(user) != null ? userInbox.get(user) : new ArrayList<Message>();
    }


    /**
     * A query for deleting Messages from a Users inbox. If any of the parameters is null the method will exit without
     * changing the map
     *
     * @param user    The user to delete messages from
     * @param message The message to delete
     */
    public void dropMessageFromUser(User user, Message message) {
        userInbox.get(user).remove(message);
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started writing " + this);
                write();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished writing Database");
            }
        }.execute();
    }

    /**
     * A query to delete all Messages from a Users inbox
     *
     * @param user The user to delete messages from
     */
    public void dropAllMessagesFromUser(User user) {
        if (userInbox.get(user) != null) //user has messages
            userInbox.get(user).clear();
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started writing " + this);
                write();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished writing Database");
            }
        }.execute();
    }

    public Message selectMessageFromUserByContents(User user, String contents) {
        if (user == null || Objects.equals(contents, ""))
            return null;
        for (Message message : userInbox.get(user))
            if (message.getContents().equals(contents))
                return message;
        return null;
    }

    public void updateMessageSeen(User user, Message message) {
        if (user == null || message == null)
            return;
        System.out.println("Update");
        System.out.println(message);
        userInbox.get(user).remove(message);
        message.setSeen(true);
        insertMessageToUser(user, message, true);
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started writing " + this);
                write();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished writing Database");
            }
        }.execute();
    }


}
