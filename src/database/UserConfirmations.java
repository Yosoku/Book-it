//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package database;

import users.User;

import java.io.Serial;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserConfirmations extends Database {
    private HashMap<User, Boolean> userConfirmed = new HashMap<User, Boolean>();
    @Serial
    private static final long serialVersionUID = 0L;

    public UserConfirmations() {
        super("src/config/userConfirmation.ser");
    }

    public void insertUserConfirmation(User user) {
        if (user == null)
            return;
        userConfirmed.putIfAbsent(user, false);
    }

    public boolean selectUserConfirmation(User user) {
        if (user == null)
            return false;
        return userConfirmed.get(user);
    }

    public void dropUserConfirmation(User user) {
        if (user == null)
            return;
        this.userConfirmed.remove(user);
    }

    public void updateUserConfirmation(User user, boolean confirmed) {
        if (user == null)
            return;
        this.userConfirmed.put(user, confirmed);
    }

    public List<User> selectAllUsersWhereConfirmedIs(boolean confirmed) {
        List<User> temp = new ArrayList<User>();
        for (User user : this.userConfirmed.keySet()) {
            if (this.userConfirmed.get(user) == confirmed) {
                temp.add(user);
            }
        }
        return temp;
    }

    public ArrayList<User> selectAllUsers() {
        return new ArrayList<>(this.userConfirmed.keySet());
    }
}
