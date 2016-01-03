package org.wizindia.black.jpa;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.domain.Roles;
import org.wizindia.black.domain.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by hari_om on 6/16/15.
 */
public class UserDaoImpl implements UserDao {
    @Autowired
    SessionFactory sessionFactory;

    final static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public User findByLogin(String login) {
        User user = null;
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("from User where login= :login");
        query.setParameter("login", login);
        List<User> userList = query.list();
        userList.get(0).getRoles();
        if(CollectionUtils.isNotEmpty(userList))
            user = userList.get(0);
        return user;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        User user = null;
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("from User where login= :login and password= :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        List<User> userList = query.list();
        if(CollectionUtils.isNotEmpty(userList))
            user = userList.get(0);
        return user;
    }

    @Override
    public User findById(int userId) {
        User user = null;
        Session session = sessionFactory.getCurrentSession();
        Query query =session.createQuery("from User where id= :userId");
        query.setParameter("userId", userId);
        List<User> userList = query.list();
        if(CollectionUtils.isNotEmpty(userList))
            user = userList.get(0);
        return user;
    }

    @Override
    public User save(User user, Set<Role> roleSet) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        Set<Roles> rolesSet=new HashSet<Roles>();
        for(Role role: roleSet) {
            session.save(new Roles(0, role, user));
            rolesSet.add(new Roles(0, role, user));
        }
        user.setRolesSet(rolesSet);
        return user;
    }
}
