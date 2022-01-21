package backend.accommodations.auth;

import backend.auth.Credentials;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class CredentialsTest {
    private static Credentials credentials;
    private static Credentials same;
    private static Credentials greekE;

    @BeforeAll
    static void setUp() {
        credentials = new Credentials("Edward", "password");
        same = new Credentials("Edward", "password");
        greekE = new Credentials("Εdward", "password");
    }

    @org.junit.jupiter.api.Test
    void testToString() {
        assertEquals(credentials.toString(), "Edwardpassword");
    }

    @org.junit.jupiter.api.Test
    void testEquals() {

        assertEquals(same, credentials);
        assertNotEquals(greekE, credentials);
    }

    @org.junit.jupiter.api.Test
    void testHashCode() {
        assertEquals(same.hashCode(), credentials.hashCode());
        assertNotEquals(credentials.hashCode(), greekE.hashCode());
    }

    @org.junit.jupiter.api.Test
    void username() {
        assertEquals(credentials.username(), same.username());
        assertNotEquals(credentials.username(), greekE.username());
    }

    @org.junit.jupiter.api.Test
    void password() {
        assertEquals(credentials.password(), same.password());

    }
}