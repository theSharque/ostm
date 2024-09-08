package global.ostm.backend.core;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

@Slf4j
public class CryptUtils {

    public static String encryptSha1(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(password.getBytes(Charset.defaultCharset()));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            log.error("No such algorithm", e);
        }

        return sha1;
    }

    public static String encryptMd5(String password) {
        String sha1 = "";
        try {
            MessageDigest crypt = MessageDigest.getInstance("MD5");
            crypt.reset();
            crypt.update(password.getBytes(Charset.defaultCharset()));
            sha1 = byteToHex(crypt.digest());
        } catch (NoSuchAlgorithmException e) {
            log.error("No such algorithm", e);
        }

        return sha1;
    }

    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash) {
            formatter.format("%02x", b);
        }

        String result = formatter.toString();
        formatter.close();

        return result;
    }
}
