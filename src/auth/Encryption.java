package auth;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>
 * A Credentials class representing credentials in an authentication process. It provides 2 final members (username/password)
 * and 2 getter methods for accessing said fields. It also provides a toString() override useful for printing the class
 * in a stream or hashing the contents of the class. The class is very similar to PasswordAuthentication
 * </p>
 *
 * @author Edward Koulakidis
 * @see java.net.PasswordAuthentication
 */
public class Encryption {
    /**
     * A method used for hashing a String input using the technique of MessageDigest class. It is commonly used partnered
     * with the Credentials record
     *
     * @param input the input string to hash
     * @return A string representation of the hash produced by SHA-512
     * @throws NoSuchAlgorithmException This exception will never reach,but semantics
     * @see auth.Credentials
     */
    public static String SHA_512(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(bytes);
        bytes = messageDigest.digest();
        return String.format("%032X", new BigInteger(1, bytes));
    }
}
