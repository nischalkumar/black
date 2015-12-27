package org.wizindia.black.utils;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.regex.Pattern;

/**
 * Created by nischal.k on 27/12/15.
 */
public class EncryptionUtils {
    @Autowired
    @Qualifier("encrypter")
    private Base64EncodedCiphererWithStaticKey encrypter;
    @Autowired
    @Qualifier("decrypter")
    private Base64EncodedCiphererWithStaticKey decrypter;


    private final static Pattern ENCODE_REGEX_CHARS = Pattern.compile("/");
    private final static Pattern DECODE_REGEX_CHARS = Pattern.compile(":::::");

    public String encrypt(String msg) {
        return escapeSpecialRegexChars(encrypter.encrypt(msg));
    }

    public String decrypt(String encryptedMsg) {
        return decrypter.encrypt(reEscapeSpecialRegexChars(encryptedMsg));
    }

    public String escapeSpecialCharacter(String msg) {
        return msg.replaceAll("[^\\w]", "-");
    }

    private String escapeSpecialRegexChars(String str) { return ENCODE_REGEX_CHARS.matcher(str).replaceAll(":::::"); }

    private static String reEscapeSpecialRegexChars(String str) { return DECODE_REGEX_CHARS.matcher(str).replaceAll("/"); }
}
