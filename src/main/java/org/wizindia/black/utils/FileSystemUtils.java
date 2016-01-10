package org.wizindia.black.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.common.Configs;
import org.wizindia.black.common.FinalFilePathContext;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by nischal.k on 07/12/15.
 */
public final class FileSystemUtils {
    @Autowired
    EncryptionUtils encryptionUtils;

    private static final String feedIdSeparator = "+++";
    private static final String reverseFeedIdSeparator = "\\+++";
    private static final String Un_Auth_Download_Endpoint = "/v1/anon/file/";
    private static final String Auth_Download_Endpoint = "/v1/file/";

    public String getDownloadLink(final long feedId, final String contextId, boolean isAuthRequired) {
        try {
            String encodedDownloadContext = encryptionUtils.encrypt(getDownloadContext(feedId, contextId));
            URL url = getDownloadUrlEndPoint(encodedDownloadContext, isAuthRequired);
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

    private URL getUnAuthEndpointUrl(String encodedDownloadContext) throws MalformedURLException {
        return new URL(Configs.baseUrl + Un_Auth_Download_Endpoint + encodedDownloadContext);
    }

    private URL getAuthEndpointUrl(String encodedDownloadContext) throws MalformedURLException {
        return new URL(Configs.baseUrl + Auth_Download_Endpoint + encodedDownloadContext);
    }

    private URL getDownloadUrlEndPoint(String encodedDownloadContext, boolean isAuthRequired) throws MalformedURLException{
        if(isAuthRequired)
            return getAuthEndpointUrl(encodedDownloadContext);
        return getUnAuthEndpointUrl(encodedDownloadContext);
    }
}
