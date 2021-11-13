import accommondations.Accommondation;
import users.Broker;
import users.User;

public class Main {

    public static void main(String[] args) {
        Broker broker = new Broker("Edward",23,"yosoku","edward@com", User.Gender.MALE,"34444", "google");
        System.out.println(broker.toString());
        Accommondation acc = new Accommondation(100,"adress",broker,null,"gamw taspitakia");
        System.out.println(acc.toString());
    }
}
