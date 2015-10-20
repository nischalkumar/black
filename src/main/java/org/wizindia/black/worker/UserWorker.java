package org.wizindia.black.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.jpa.UserDao;
import org.wizindia.black.domain.User;

import java.util.Set;

/**
 * Created by hari_om on 6/14/15.
 */
public class UserWorker {
    @Autowired
    private UserDao userDao;

    public UserWorker() {}

    public User getUser(String username) {
        return userDao.findByLogin(username);
    }

    public User insertUser(User user, Set<Role> roleSet) {
        return userDao.save(user, roleSet);
    }
}
