package org.wizindia.black.jpa;

import org.springframework.stereotype.Repository;
import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.validation.Policy;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hari_om on 27/9/15.
 */
@Repository(value = "RoleDao")
@Transactional
public interface RoleDao {
    List<Policy> getPolicy(Role role);
}
