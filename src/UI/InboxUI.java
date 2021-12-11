package UI;

import application.DatabaseAPI;
import communication.Message;
import users.User;

import java.util.List;

public class InboxUI extends UI {
    private User user;

    public InboxUI(User user) {
        this.user = user;
    }

    @Override
    public void show() {
        List<Message> inbox = DatabaseAPI.getUserMessagesDatabase().selectMessageFromUser(user);
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
                    if (Message.auth.equals(message.getSubject()))
                        DatabaseAPI.getUserConfirmationsDatabase().updateUserConfirmation(user);
                }
            }
        }
    }
}
