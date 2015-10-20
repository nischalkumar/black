package org.wizindia.black.common;

/**
 * Created by hari_om on 27/9/15.
 */
public enum PolicyEnum {
    ALL_TEACHER(100, "ALL_TEACHER"),
    ALL_STUDENT(100, "ALL_STUDENT");
    long policyCode;
    String policyName;
    private PolicyEnum(long policyCode, String policyName) {
        this.policyCode = policyCode;
        this.policyName = policyName;
    }

    public Long getPolicyCode() {
        return policyCode;
    }

    public String getPolicyName() {
        return policyName;
    }
}
