package communication;

import users.User;

public class Message {
    private User sender;
    private User receiver;
    private String contents;
    private boolean seen;



    public Message(User sender, User receiver, String contents) {
        this.sender = sender;
        this.receiver = receiver;
        this.contents = contents;
        seen = false;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }


}
