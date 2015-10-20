package org.wizindia.black.jpa;

import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.common.PolicyEnum;
import org.wizindia.black.validation.Policy;
import org.wizindia.black.validation.PolicyAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hari_om on 27/9/15.
 */
public class RoleDaoMock implements RoleDao {
    public List<Policy> getPolicy(Role role) {
        PolicyAttribute policyAttribute=new PolicyAttribute("ABCD");
        Map<PolicyAttribute, List<Object>> policyAttributeObjectMap= new HashMap<>();
        policyAttributeObjectMap.put(policyAttribute, new ArrayList<Object>());
        Policy policy=new Policy(PolicyEnum.ALL_STUDENT, policyAttributeObjectMap);
        List<Policy> policyList = new ArrayList<>();
        policyList.add(policy);
        return policyList;
    }
}
