package UI;

public class AdminUI extends UI {
    @Override
    public void show() {
        System.out.println("Welcome Admin\nHow would you like to proceed?");
        System.out.println("Confirm new Users(confirm)\nView statistics(stats)\n");
        request = getInput("Enter confirm/stats", "(confirm|stats)");
    }


}
