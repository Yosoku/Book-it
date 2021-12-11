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


/**
 * <p>
 * The UserConfirmations is a class intended to handle information about accounts in an application. It is implemented by a
 * Hashtable holding Users->Boolean. It basically holds information about a Users confirmation status</p>
 * <p>
 * The UserConfirmations inherits from Database inheriting read/write methods and implementing Serializable interface. It also
 * provides basic queries like SELECT,INSERT and DROP. It is also important to note that <b>null</b> values are not stored
 * in this class</p>
 *
 * @author Edward Koulakidis
 * @see java.io.Serializable
 * @see Database
 */

public class UserConfirmationsDB extends Database {
    @Serial
    private static final long serialVersionUID = 0L;
    private HashMap<User, Boolean> userConfirmed;

    /**
     * An initializer constructor used to initialize the map and call the Base class with the specified filename to store
     * the map
     */
    public UserConfirmationsDB() {
        super("src/config/userConfirmation.ser");
        userConfirmed = new HashMap<User, Boolean>();
    }

    /**
     * A query for inserting new Users in the Database. If any of the parameters is <b>null</b> the method will exit
     * without changing the Hashmap. Runs in O(1) time
     *
     * @param user The new User to insert as confirmed=false
     */
    public void insertUserConfirmation(User user) {
        if (user == null)
            return;
        userConfirmed.put(user, false); // Users are initially inserted as false
    }

    /**
     * A query for selecting Users in the Database. If any of the parameters is <b>null</b> the method will exit
     * without changing the Hashmap. Runs in O(1) time
     *
     * @param user The new User to select
     * @return Returns the boolean status of the User's confirmation status. Returns <b>ALWAYS</b> false if the user is null
     */
    public boolean selectUserConfirmation(User user) {
        if (user == null)
            return false;
        return userConfirmed.get(user);
    }

    /**
     * A query for dropping(deleting) Users from the Database. If any of the parameters is <b>null</b> the method will exit
     * without changing the Hashmap. Runs in O(1) time
     *
     * @param user The User to delete from the map
     */
    public void dropUserConfirmation(User user) {
        if (user == null)
            return;
        userConfirmed.remove(user);
    }

    /**
     * A query for updating current Users in the Database and setting their confirmation to true. If the user parameter
     * is <b>null</b> the method will exit without changing the Hashmap. Runs in O(1) time
     *
     * @param user The User to update from the map
     */
    public void updateUserConfirmation(User user) {
        if (user == null)
            return;
        userConfirmed.put(user, true);
    }

    /**
     * A query for selecting <b>ALL</b> Users from the Database whose confirmation status==confirmed. Runs in O(n) time
     * Example: <code>selectAllUsersWhereConfirmedIs(true)</code> returns all the Users who have confirmation status
     * as true
     *
     * @param confirmed The boolean status to query
     * @return A List with all the Users with confirmation status==confirmed
     */
    public List<User> selectAllUsersWhereConfirmedIs(boolean confirmed) {
        List<User> temp = new ArrayList<User>();
        for (User user : this.userConfirmed.keySet()) {
            if (userConfirmed.get(user) == confirmed) {
                temp.add(user);
            }
        }
        return temp;
    }

    /**
     * A method useful for selecting all the Users in the Database. Runs in O(1) time
     *
     * @return Returns an ArrayList with all the Users registered in the map regardless of confirmation status
     */
    public ArrayList<User> selectAllUsers() {
        return new ArrayList<>(userConfirmed.keySet());
    }
}
