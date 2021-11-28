package auth;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * A Credentials record representing credentials in an authentication process. It provides 2 final members (username/password)
 * and 2 getter methods for accessing said fields. It also provides a toString() override useful for printing the class
 * in a stream or hashing the contents of the class. The record is very similar to PasswordAuthentication
 * </p>
 *
 * @author Edward Koulakidis
 * @see java.net.PasswordAuthentication
 */
public record Credentials(String username, String password) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    /**
     * A toString override useful for hashing a User's credentials in a Database
     *
     * @return The username concatenated with the password
     * @see database.Database
     * @see Encryption
     */
    @Override
    public String toString() {
        return username + password;
    }

}
