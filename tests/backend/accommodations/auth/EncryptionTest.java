package backend.accommodations.auth;

import backend.auth.Credentials;
import backend.auth.Encryption;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class EncryptionTest {

    @org.junit.jupiter.api.Test
    void SHA_512() {
        Credentials credentials = new Credentials("Edward", "password");
        String hash1 = Encryption.SHA_512(credentials.toString());
        String hash2 = Encryption.SHA_512(new Credentials("Edward", "password").toString());
        String hash3 = Encryption.SHA_512(new Credentials("Εdward", "password").toString());
        assertEquals(hash1, hash2);
        assertNotEquals(hash1, hash3);//greekE
    }
}