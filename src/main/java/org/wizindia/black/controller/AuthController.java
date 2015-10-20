package org.wizindia.black.controller;

/**
 * Created by hari_om on 6/18/15.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.wizindia.black.utils.AuthObject;
import org.wizindia.black.common.PolicyEnum;
import org.wizindia.black.jpa.UserDao;
import org.wizindia.black.domain.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AuthController {
    @Autowired
    UserDao userDao;

    Map<String, List<PolicyEnum>> policyEnumMap;

    public AuthController() {}

    public AuthObject getAuthObject(Authentication authentication) {
        LinkedHashMap<String, String> genericAuthObject = (LinkedHashMap<String, String>) (((OAuth2Authentication) authentication).getUserAuthentication().getDetails());
        return new AuthObject(genericAuthObject);
    }

    public User getUser(Authentication authentication) {
        LinkedHashMap<String, String> genericAuthObject = (LinkedHashMap<String, String>) (((OAuth2Authentication) authentication).getUserAuthentication().getDetails());
        AuthObject authObject=new AuthObject(genericAuthObject);
         return userDao.findByLogin(authObject.getUsername());
    }

    public void setPolicies(Map<String, List<PolicyEnum>> policyEnumMap) {
        this.policyEnumMap = policyEnumMap;
    }
}
