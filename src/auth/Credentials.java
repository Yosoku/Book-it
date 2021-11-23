package auth;

public record Credentials(String username, String password) {

    @Override
    public String toString() {
        return username + password;
    }

}
