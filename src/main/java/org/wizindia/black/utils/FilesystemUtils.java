package org.wizindia.black.utils;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.FileSystemEnum;
import org.wizindia.black.jpa.FileSystem;

import java.io.File;

/**
 * Created by nischal.k on 07/12/15.
 */
public class FileSystemUtils {
    @Autowired
    @Qualifier("encrypter")
    private Base64EncodedCiphererWithStaticKey encrypter;

    public String getDownloadLink(String finalContext, String originalFileName, String fileName) {
        return Configs.baseUrl+"/v1/file/"+encrypter.encrypt(finalContext + "/" + fileName);
    }

    public String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}
