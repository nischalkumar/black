package org.wizindia.black.utils;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.FileSystemEnum;
import org.wizindia.black.jpa.FileSystem;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.regex.Pattern;

/**
 * Created by nischal.k on 07/12/15.
 */
public class FileSystemUtils {
    @Autowired
    @Qualifier("encrypter")
    private Base64EncodedCiphererWithStaticKey encrypter;
    @Autowired
    @Qualifier("decrypter")
    private Base64EncodedCiphererWithStaticKey decrypter;

    Pattern ENCODE_REGEX_CHARS = Pattern.compile("/");
    Pattern DECODE_REGEX_CHARS = Pattern.compile(":::::");

    public String getDownloadLink(final String finalContext) {
        try {
            String encoded = encrypter.encrypt(finalContext);
            encoded = escapeSpecialRegexChars(encoded);
            URL url = new URL("https://" + Configs.baseUrl + "/v1/file/" + encoded);
            String nullFragment = null;
            URI uri = new URI(url.getProtocol(), url.getHost()+":"+url.getPort(), url.getPath(), url.getQuery(), nullFragment);

            url = uri.toURL();
            return uri.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return new StringBuilder().toString();
    }

    public String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    public String getOriginalContextFromEncryptedOriginalContext(final String encryptedFinalContext) {
        return decrypter.encrypt(reEscapeSpecialRegexChars(encryptedFinalContext));
    }

    private String escapeSpecialRegexChars(String str) {
        return ENCODE_REGEX_CHARS.matcher(str).replaceAll(":::::");
    }

    String reEscapeSpecialRegexChars(String str) {

        return DECODE_REGEX_CHARS.matcher(str).replaceAll("/");
    }
}
