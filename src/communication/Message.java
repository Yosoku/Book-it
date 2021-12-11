package communication;

import users.User;

import java.io.Serializable;

public class Message implements Serializable {
    private final User sender;
    private final User receiver;
    private final String subject;
    private final String contents;
    private boolean seen;
    public static final String auth = "Authemtication email";

    public Message(User sender, User receiver, String subject, String contents) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.contents = contents;
        seen = false;
    }

    public String getSubject() {
        return subject;
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


    public User getReceiver() {
        return receiver;
    }


    public String getContents() {
        return contents;
    }


    public void readMessage() {
        setSeen(true);
        System.out.println(contents);
    }

    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", contents='" + contents + '\'' +
                ", seen=" + seen +
                '}';
    }
}
