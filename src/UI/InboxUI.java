package UI;

import application.DatabaseAPI;
import communication.Message;
import config.Configurations;
import users.User;

import java.util.List;

/**
 * An Inbox User Interface class to represent an Inbox
 *
 * @author Fwteinos Wawaroutas
 */
public class InboxUI extends UI {
    private User user;


    /**
     * Initializer constructor
     *
     * @param user The current Customer
     */
    public InboxUI(User user) {
        this.user = user;
    }


    //TODO add send message
    /**
     * Method to show different options to read an Inbox
     */
    @Override
    public void show() {
        List<Message> inbox = DatabaseAPI.userMessagesDatabase.selectMessageFromUser(user);
        if (inbox == null) {
            System.out.println("Your inbox is empty");
        } else {
            System.out.println("You have " + inbox.size() + "messages");
            for (Message message : inbox) {
                char ans = getInput("Would you like to read the next Message?y/n", "(y|Y|N|n)").charAt(0);
                if (ans == 'n' || ans == 'N')
                    return;
                else {
                    message.readMessage();
                    if (Configurations.CONFIRMATION_MESSAGE_SUBJECT.equals(message.getSubject()))
                        DatabaseAPI.userConfirmationsDatabase.updateUserConfirmation(user);
                }
            }
        }
    }
}
