package org.wizindia.black.utils;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.FileSystemEnum;
import org.wizindia.black.jpa.FileSystem;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

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

    public String getDownloadLink(final String finalContext) {
        try {
            return Configs.baseUrl + "/v1/file/" + (String)URLEncoder.encode(encrypter.encrypt(finalContext), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return new StringBuilder().toString();
        }
    }

    public String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }

    public String getOriginalContextFromEncryptedOriginalContext(final String encryptedFinalContext) {
        return decrypter.encrypt(encryptedFinalContext);
    }
}
