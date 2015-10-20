package org.wizindia.black.jpa;

import org.springframework.stereotype.Repository;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.domain.User;

import java.util.Set;

/**
 * Created by hari_om on 6/16/15.
 */
@Repository(value = "UserDao")
public interface UserDao {
    User findByLogin(String login);
    User findByLoginAndPassword(String login, String password);
    User findById(int userId);
    User save(User User, Set<Role> roleSet);
}
