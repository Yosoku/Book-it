package auth;

import java.io.Serial;
import java.io.Serializable;

public record Credentials(String username, String password) implements Serializable {
    @Serial
    private static final long serialVersionUID = 0;

    @Override
    public String toString() {
        return username + password;
    }

}
