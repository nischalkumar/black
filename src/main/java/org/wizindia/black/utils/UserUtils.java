package org.wizindia.black.utils;

import com.springcryptoutils.core.cipher.symmetric.Base64EncodedCiphererWithStaticKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.domain.User;
import org.wizindia.black.common.response.UserResponse;

import java.util.Collections;
import java.util.Set;

/**
 * Created by hari_om on 14/9/15.
 */
public class UserUtils {

    @Autowired
    @Qualifier("encrypter")
    private Base64EncodedCiphererWithStaticKey encrypter;

    @Autowired
    @Qualifier("decrypter")
    private Base64EncodedCiphererWithStaticKey decrypter;

    public UserUtils() {
    }

    public UserResponse getEncryptedUser(User user) {
        return new UserResponse(encrypter.encrypt(user.getId().toString()), user.getLogin(), user.getRoles());
    }

    public Set<Role> getRoleSet(Integer id) {
        Set<Role> roleSet= Collections.emptySet();
        /*
        for(Roles roles : roleDao.getRoles(id)) {
            roleSet.add(roles.getRoleSet());
        }
        */
        return roleSet;
    }

    //TODO: add a catch for exception
    public Integer getDecryptedId(String encryptedId) {
        return Integer.parseInt(decrypter.encrypt(encryptedId));
    }

}
