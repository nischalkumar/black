package org.wizindia.black.validation;

import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hari_om on 12/10/15.
 */
public class PolicyValidatorContext {
    List<Role> roleList;
    User user;

    public PolicyValidatorContext(User user) {
        this.roleList = new ArrayList<>();
        this.user = user;
    }

    public PolicyValidatorContext(User user, List<Role> roleList) {
        this.roleList = roleList;
        this.user = user;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public User getUser() {
        return user;
    }

    public void addRole(Role role) {
        roleList.add(role);
    }
}
