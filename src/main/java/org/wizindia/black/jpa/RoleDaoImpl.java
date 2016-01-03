package org.wizindia.black.jpa;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.validation.Policy;

import java.util.List;

/**
 * Created by nischal.k on 10/12/15.
 */
public class RoleDaoImpl implements RoleDao{

    @Autowired
    SessionFactory sessionFactory;

    final static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public List<Policy> getPolicy(Role role) {
        return null;
    }
}
