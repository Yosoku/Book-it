package database;

import auth.Credentials;
import auth.Encryption;
import users.User;

import java.io.Serial;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class CredentialsUser extends Database {
    @Serial
    private static final long serialVersionUID = 0;
    private HashMap<String, User> users;

    public CredentialsUser() {
        super("src/config/credentialsUser.ser");
        users = new HashMap<String, User>();
    }

    public void insertUser(Credentials credentials, User user) throws NoSuchAlgorithmException {
        if (user == null || credentials == null)
            return;
        String hash = Encryption.SHA_512(credentials.toString());
        if (!users.containsKey(hash))
            users.put(hash, user);
    }

    public User selectUser(Credentials credentials) throws NoSuchAlgorithmException {
        String hash = Encryption.SHA_512(credentials.toString());
        if (users.containsKey(hash))
            return users.get(hash);
        return null;
    }

    public void dropUser(Credentials credentials, User user) throws NoSuchAlgorithmException {
        if (credentials == null || user == null)
            return;
        String hash = Encryption.SHA_512(credentials.toString());
        users.remove(hash);
    }

}
