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
public class FileSystemUtils {
    @Autowired
    @Qualifier("encrypter")
    private Base64EncodedCiphererWithStaticKey encrypter;
    @Autowired
    @Qualifier("decrypter")
    private Base64EncodedCiphererWithStaticKey decrypter;

    private final Pattern ENCODE_REGEX_CHARS = Pattern.compile("/");
    private final Pattern DECODE_REGEX_CHARS = Pattern.compile(":::::");
    private final String feedIdSeparator = "+++++++++++";
    public String getDownloadLink(final long feedId, final long contextId) {
        try {
            String encodedDownloadContext = encrypter.encrypt(getDownloadContext(feedId, contextId));
            encodedDownloadContext = escapeSpecialRegexChars(encodedDownloadContext);
            URL url = new URL("https://" + Configs.baseUrl + "/v1/file/" + encodedDownloadContext);
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

    public FinalFilePathContext getOriginalContextFromEncryptedOriginalContext(final String encryptedFinalContext) {
        String downloadContext = decrypter.encrypt(reEscapeSpecialRegexChars(encryptedFinalContext));
        return getInverseDownloadContext(downloadContext);
    }

    private FinalFilePathContext getInverseDownloadContext(String downloadContext) {
        String[] ar = downloadContext.split(feedIdSeparator);
        return new FinalFilePathContext(Long.parseLong(ar[0]), Long.parseLong(ar[1]));
    }

    private String getDownloadContext(long feedId, long contextId) {
        return Long.toString(feedId) + feedIdSeparator + Long.toString(contextId);
    }

    private String escapeSpecialRegexChars(String str) {
        return ENCODE_REGEX_CHARS.matcher(str).replaceAll(":::::");
    }

    String reEscapeSpecialRegexChars(String str) {

        return DECODE_REGEX_CHARS.matcher(str).replaceAll("/");
    }
}
