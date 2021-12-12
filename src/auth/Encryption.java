package auth;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * An Encryption class useful for encrypting messages in an authentication process. It provides a static method for Encrypting
 * a message using the SHA-512 algorithm via the MessageDigest class.
 * </p>
 *
 * @author Edward Koulakidis
 * @see MessageDigest
 * @see <a href="https://en.wikipedia.org/wiki/SHA-2">SHA-2</a>
 */
public class Encryption {
    /**
     * A method used for hashing a String input using the technique of MessageDigest class. It is commonly used partnered
     * with the Credentials record
     *
     * @param input the input string to hash
     * @return A string representation of the hash produced by SHA-512
     * @see auth.Credentials
     */
    public static String SHA_512(String input) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(bytes);
        bytes = messageDigest.digest();
        return String.format("%032X", new BigInteger(1, bytes));
    }
}
