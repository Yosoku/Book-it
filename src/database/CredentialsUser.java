package database;

import auth.Credentials;
import auth.Encryption;
import users.User;

import java.io.Serial;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;


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


public class CredentialsUser extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private HashMap<String, User> users; //Mapping of hash(Credentials) -> User

    /**
     * An initializer constructor used to initialize the map and call the Base class with the specified filename to store
     * the map
     */
    public CredentialsUser() {
        super("src/config/credentialsUser.ser");
        users = new HashMap<String, User>();
    }

    /**
     * A query for inserting new Users in the Database. If any of the parameters is <b>null</b> the method will exit
     * without changing the Hashmap. Runs in O(1) time
     *
     * @param credentials the users credentials
     * @param user        A user instance
     * @throws NoSuchAlgorithmException Because of Encryption.SHA_512 the method throws NoSuchAlgorithmException
     */
    public void insertUser(Credentials credentials, User user) throws NoSuchAlgorithmException {
        if (user == null || credentials == null)
            return;
        String hash = Encryption.SHA_512(credentials.toString()); //get credentials hash in a String format
        if (!users.containsKey(hash))
            users.put(hash, user);
    }

    /**
     * A query for selecting Users based on their credentials. If the credentials are found in the map the method
     * returns the User,null otherwise. Runs in O(1) time
     *
     * @param credentials The credentials used to query the Hashmap
     * @return The User mapped to the parameter credentials,if present in the map, null otherwise
     * @throws NoSuchAlgorithmException Because of Encryption.SHA_512 the method throws NoSuchAlgorithmException
     */
    public User selectUser(Credentials credentials) throws NoSuchAlgorithmException {
        if (credentials == null)
            return null;
        String hash = Encryption.SHA_512(credentials.toString());
        if (users.containsKey(hash))
            return users.get(hash);
        return null;
    }

    /**
     * A drop(delete) query for deleting entries in the map. If the parameters are null the method doesn't change the map
     *
     * @param credentials The Users.Credentials we wish to delete
     * @throws NoSuchAlgorithmException Because of Encryption.SHA_512 the method throws NoSuchAlgorithmException
     */
    public void dropUser(Credentials credentials) throws NoSuchAlgorithmException {
        if (credentials == null)
            return;
        String hash = Encryption.SHA_512(credentials.toString());
        users.remove(hash);
    }


    /**
     * A drop(delete) query for deleting entries in the map using the User. This method works as an interface to
     * dropUser(Credentials)
     *
     * @param user The User we wish to delete from the map
     * @throws NoSuchAlgorithmException Because of Encryption.SHA_512 the method throws NoSuchAlgorithmException
     */
    public void dropUser(User user) throws NoSuchAlgorithmException {
        if (user == null)
            return;
        dropUser(user.getCredentials());
    }
}
