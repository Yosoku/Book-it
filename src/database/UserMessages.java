package database;

import communication.Message;
import users.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserMessages extends Database {
    private final HashMap<User, ArrayList<Message>> userInbox;

    public UserMessages() {
        super(DatabaseType.USER_MESSAGES, "");
        userInbox = new HashMap<User, ArrayList<Message>>();
    }


    public void insertMessageToUser(User user, Message message) {
        if (user == null || message == null)
            return;
        ArrayList<Message> messagesList = userInbox.get(user);
        if (messagesList == null)
            messagesList = new ArrayList<>();
        messagesList.add(message);
        userInbox.put(user, messagesList);
    }

    public List<Message> selectMessageFromUser(User user) {
        if (userInbox.containsKey(user))
            return userInbox.get(user);
        return null;
    }

    public void dropMessageFromUser(User user, Message message) {
        if (user == null || message == null)
            return;
        userInbox.get(user).remove(message);
    }

    @Override
    public void write() {

    }

    @Override
    public void read() {

    }
}
