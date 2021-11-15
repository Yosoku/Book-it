package auth;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Encryption {
    public static String SHA_512(String input) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
        byte[] bytes = input.getBytes(StandardCharsets.UTF_8);
        messageDigest.update(bytes);
        bytes = messageDigest.digest();
        return String.format("%032X", new BigInteger(1, bytes));
    }
}
