package org.wizindia.black.utils;

import java.util.LinkedHashMap;

/**
 * Created by hari_om on 6/18/15.
 */
public class AuthObject {
    String username;

    String clientSecrent;
    String scope;
    String grantType;
    String clientId;

    public AuthObject() {}

    public AuthObject(LinkedHashMap<String, String> authObject) {
        this.username=authObject.get("username");
        this.clientSecrent=authObject.get("client_secret");
        this.scope=authObject.get("scope");
        this.grantType=authObject.get("grant_type");
        this.clientId=authObject.get("client_id");
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientSecrent() {
        return clientSecrent;
    }

    public void setClientSecrent(String clientSecrent) {
        this.clientSecrent = clientSecrent;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
