package database;

import auth.Credentials;
import auth.Encryption;
import users.User;

import javax.swing.*;
import java.io.Serial;
import java.util.HashMap;
import java.util.Objects;


/**
 * <p>
 * The CredentialsUser is a class intended to handle information about accounts in an application. It is implemented by a
 * Hashtable holding Credentials hashes as keys and Users as values</p>
 * <p>
 * The CredentialsUser inherits from Database inheriting read/write methods and implementing Serializable interface. It also
 * provides basic queries like SELECT,INSERT and DROP. It is also important to note that <b>null</b> values are not stored
 * in this class</p>
 *
 * @author Edward Koulakidis
 * @see java.io.Serializable
 * @see Database
 */


public class CredentialsUserDB extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private HashMap<String, User> users; //Mapping of hash(Credentials) -> User

    /**
     * An initializer constructor used to initialize the map and call the Base class with the specified filename to store
     * the map
     */
    public CredentialsUserDB() {
        super("src/config/credentialsUser.ser");
        users = new HashMap<String, User>();
    }

    /**
     * A query for inserting new Users in the Database. If any of the parameters is <b>null</b> the method will exit
     * without changing the Hashmap. Runs in O(1) time
     *
     * @param credentials the users credentials
     * @param user        A user instance
     */
    public void insertUser(Credentials credentials, User user) {
        if (user == null || credentials == null)
            return;
        String hash = Encryption.SHA_512(credentials.toString()); //get credentials hash in a String format
        users.put(hash, user);
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started writing " + this);
                write();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished writing Databases");
            }
        }.execute();
    }

    /**
     * A query for selecting Users based on their credentials. If the credentials are found in the map the method
     * returns the User,null otherwise. Runs in O(1) time
     *
     * @param credentials The credentials used to query the Hashmap
     * @return The User mapped to the parameter credentials,if present in the map, null otherwise
     */
    public User selectUser(Credentials credentials) {
        if (credentials == null)
            return null;
        String hash = Encryption.SHA_512(credentials.toString());
        return users.get(hash);
    }

    /**
     * A drop(delete) query for deleting entries in the map. If the parameters are null the method doesn't change the map
     *
     * @param credentials The Users.Credentials we wish to delete
     */
    public void dropUser(Credentials credentials) {
        if (credentials == null)
            return;
        String hash = Encryption.SHA_512(credentials.toString());
        users.remove(hash);
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started writing " + this);
                write();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished writing Databases");
            }
        }.execute();
    }


    /**
     * A drop(delete) query for deleting entries in the map using the User. This method works as an interface to
     * dropUser(Credentials)
     *
     * @param user The User we wish to delete from the map
     */
    public void dropUser(User user) {
        if (user == null)
            return;
        dropUser(user.getCredentials());
        new SwingWorker<>() {
            @Override
            protected Object doInBackground() {
                System.out.println("Started writing " + this);
                write();
                return null;
            }

            @Override
            protected void done() {
                System.out.println("Finished writing Databases");
            }
        }.execute();
    }


    public User selectUserByUsername(String username) {
        if (username == null || username.equals(""))
            return null;
        else {
            for (User user : users.values())
                if (Objects.equals(user.getCredentials().username(), username))
                    return user;
        }
        return null;
    }
}
