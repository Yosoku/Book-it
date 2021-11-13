import communication.Messenger;
import users.Broker;
import users.User;

public class Main {

    public static void main(String[] args) {
        Broker broker1 = new Broker("Edward",23,"yosoku",
                "edward@com", User.Gender.MALE,"34444", "google");
        Broker broker2 = new Broker("Aglaia",20,"aglaia123",
                "aglaia@com", User.Gender.FEMALE,"334343434","youtube");
        Messenger m = new Messenger();
        m.sendMessage("Tha pas sxoli?",broker1,broker2);
        broker2.viewAllMessages();
    }
}
