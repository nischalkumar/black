package org.wizindia.black.common.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.wizindia.black.common.Enums.Role;

import java.util.Set;

/**
 * Created by hari_om on 6/28/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    @JsonProperty("id")
    private String id;

    @JsonProperty("login")
    private String login;


    @JsonProperty("roles")
    private Set<Role> roleSet;

    public UserResponse() {}

    public UserResponse(String login, Set<Role> roleSet) {
        this.login = login;
        this.roleSet = roleSet;
    }

    public UserResponse(String id, String login, Set<Role> roleSet) {
        this.id = id;
        this.login = login;
        this.roleSet = roleSet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }
}