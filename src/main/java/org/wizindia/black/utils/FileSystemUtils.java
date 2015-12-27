package org.wizindia.black.utils;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.FileSystemEnum;
import org.wizindia.black.common.FinalFilePathContext;
import org.wizindia.black.jpa.FileSystem;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.regex.Pattern;

/**
 * Created by nischal.k on 07/12/15.
 */
public final class FileSystemUtils {
    @Autowired
    EncryptionUtils encryptionUtils;

    private final String feedIdSeparator = "+++";
    private final String reverseFeedIdSeparator = "\\+++";
    public String getDownloadLink(final long feedId, final String contextId) {
        try {
            String encodedDownloadContext = encryptionUtils.encrypt(getDownloadContext(feedId, contextId));
            URL url = new URL(Configs.baseUrl + "/v1/file/" + encodedDownloadContext);
            String nullFragment = null;
            URI uri = new URI(url.getProtocol(), url.getHost()+":"+url.getPort(), url.getPath(), url.getQuery(), nullFragment);
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
            return new StringBuilder().toString();
        }
    }

    public FinalFilePathContext getOriginalContextFromEncryptedOriginalContext(final String encryptedFinalContext) {
        return getInverseDownloadContext(encryptionUtils.decrypt(encryptedFinalContext));
    }

    private FinalFilePathContext getInverseDownloadContext(String downloadContext) {
        String[] ar = downloadContext.split(reverseFeedIdSeparator);
        return new FinalFilePathContext(Long.parseLong(ar[0]), ar[1]);
    }

    private String getDownloadContext(long feedId, String contextId) {
        return Long.toString(feedId) + feedIdSeparator + contextId;
    }
}
