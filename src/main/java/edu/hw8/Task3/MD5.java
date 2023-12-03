package edu.hw8.Task3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

    @SuppressWarnings("MethodName")
    public static String MD5Hash(String password) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(password.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("MagicNumber")
    private static String bytesToHex(byte[] array) {
        char[] val = new char[2 * array.length];
        String hex = "0123456789abcdef";
        for (int i = 0; i < array.length; i++) {
            int b = array[i] & 0xff;
            val[2 * i] = hex.charAt(b >>> 4);
            val[2 * i + 1] = hex.charAt(b & 15);
        }
        return String.valueOf(val);
    }

    private MD5() {
    }
}
