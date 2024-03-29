package PassApp;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class Account {
    private static final SecureRandom RAND = new SecureRandom();
    String User;
    String Hash;
    String Salt;

    /*
     *
     * Constructor for an Account that stores a users information
     * Does not store password directly but takes it as a paramater and users it to generate a hash
     * */
    public Account(String user, char[] password) throws Exception {
        this.User = user;
        this.Salt = generateSalt(20);
        String pass = new String(password);
        this.Hash = generateHash(pass, this.Salt);

    }

    Account(String user, String hash, String salt) {
        User = user;
        Hash = hash;
        Salt = salt;
    }

    private static String generateSalt(final int length) {

        if (length < 1) {
            System.out.println("Error length must be greater than 0");
            return "";
        }
        byte[] salt = new byte[length];
        RAND.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String generateHash(String password, String salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        String pass = password + salt;
        byte[] hash = digest.digest(pass.getBytes());
        return bytesToHex(hash);


    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
