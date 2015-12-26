package org.wizindia.black.utils;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.wizindia.black.common.request.ContextRequest;
import org.wizindia.black.domain.Context;

/**
 * Created by nischal.k on 26/12/15.
 */
public class FeedUtils {

    @Autowired
    @Qualifier("encrypter")
    private Base64EncodedCiphererWithStaticKey encrypter;
    @Autowired
    @Qualifier("decrypter")
    private Base64EncodedCiphererWithStaticKey decrypter;

    public ContextRequest getContextRequest(Context context) {
        return new ContextRequest(encrypter.encrypt(Long.toString(context.getContextId())), context.getFolderPath(), context.getMaxFileSize(), context.getMinFileSize(), context.getAllowedExtensions(), context.isAuthRequired());
    }
}
