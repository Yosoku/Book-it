package auth;

public class Credentials {
    private final String username;
    private final String password;

    public Credentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return username + password;
    }
}
