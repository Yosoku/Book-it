package database;

import auth.Credentials;
import auth.Encryption;
import users.User;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class CredentialsUser extends Database {
    private final HashMap<String, User> users;

    public CredentialsUser() {
        super(DatabaseType.CREDENTIALS_USER, "");
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
        String hash = Encryption.SHA_512(credentials.toString());
        if (credentials == null || user == null)
            return;
        users.remove(hash);
    }

    @Override
    public void write() {

    }

    @Override
    public void read() {

    }
}
