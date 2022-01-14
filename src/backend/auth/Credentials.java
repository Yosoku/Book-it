package backend.auth;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

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
     * @see backend.database.Database
     * @see Encryption
     */
    @Override
    public String toString() {
        return username + password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credentials that = (Credentials) o;
        return username.equals(that.username) && password.equals(that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
