package communication;

import users.User;

public class Messenger {

    public void sendMessage(String s, User sender, User receiver) {
        receiver.addMessage(new Message(sender,receiver,s));
    }
}
