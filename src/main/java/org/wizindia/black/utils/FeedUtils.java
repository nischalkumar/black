package org.wizindia.black.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.common.request.ContextRequest;
import org.wizindia.black.domain.Context;

/**
 * Created by nischal.k on 26/12/15.
 */
public class FeedUtils {
    @Autowired
    EncryptionUtils encryptionUtils;

    @Autowired
    FileSystemUtils fileSystemUtils;

    public ContextRequest getContextRequest(Context context) {
        return new ContextRequest(context.getContextId(), context.getFolderPath(), context.getMaxFileSize(), context.getMinFileSize(), context.getAllowedExtensions(), context.isAuthRequired());
    }

    public long getContextId(String encryptedContextId) {
        return Long.parseLong(encryptionUtils.decrypt(encryptedContextId));
    }
}
