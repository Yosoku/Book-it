package backend.communication;

import backend.users.User;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * A class representing a Message instace with information about the Sender,Receiver,the subject and the contents of Message
 * The class also implements the Serializable interface for storing in files
 *
 * @author Edward Koulakidis
 */
public class Message implements Serializable {
    @Serial
    private static long serialversionUID = 0;
    private final User sender;
    private final User receiver;
    private final String subject;
    private final String contents;
    private boolean seen;

    public Message(User sender, User receiver, String subject, String contents) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.contents = contents;
        seen = false;
    }

    //==================================================Getters & Setters ==============================================
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

    /**
     * Reading the message simply prints the contents to the console and sets seen to true
     */
    public void readMessage() {
        setSeen(true);
        System.out.println(contents);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return seen == message.seen && Objects.equals(sender, message.sender) && Objects.equals(receiver, message.receiver) && Objects.equals(subject, message.subject) && Objects.equals(contents, message.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, subject, contents, seen);
    }

    /**
     * Useful for printing to the user and also debugging
     *
     * @return a string representation of the Message instance
     */
    @Override
    public String toString() {
        return "Message{" +
                "sender=" + sender +
                ", contents='" + contents + '\'' +
                ", seen=" + seen +
                '}';
    }
}
