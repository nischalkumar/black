package org.wizindia.black.validation;

import org.wizindia.black.common.PolicyEnum;

import java.util.List;
import java.util.Map;

/**
 * Created by hari_om on 27/9/15.
 */
public class Policy {
    PolicyEnum PolicyName;
    Map<PolicyAttribute, List<Object>> attributeListMap;

    public Policy() {
    }

    public Policy(PolicyEnum policyName, Map<PolicyAttribute, List<Object>> attributeListMap) {
        PolicyName = policyName;
        this.attributeListMap = attributeListMap;
    }

    public PolicyEnum getPolicyName() {
        return PolicyName;
    }

    public void setPolicyName(PolicyEnum policyName) {
        PolicyName = policyName;
    }

    public Map<PolicyAttribute, List<Object>> getAttributeListMap() {
        return attributeListMap;
    }

    public void setAttributeListMap(Map<PolicyAttribute, List<Object>> attributeListMap) {
        this.attributeListMap = attributeListMap;
    }
}
