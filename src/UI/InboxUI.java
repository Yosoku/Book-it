package UI;

import communication.Message;

import java.util.List;

public class InboxUI extends UI {
    private List<Message> inbox;

    public InboxUI(List<Message> inbox) {
        this.inbox = inbox;
    }

    @Override
    public void show() {

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
                }
            }
        }
    }
}
