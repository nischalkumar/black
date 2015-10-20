package org.wizindia.black.validation;

/**
 * Created by hari_om on 27/9/15.
 */
public class PolicyAttribute {
    String AttributeName;

    public PolicyAttribute() {
    }

    public PolicyAttribute(String attributeName) {
        AttributeName = attributeName;
    }

    public String getAttributeName() {
        return AttributeName;
    }

    public void setAttributeName(String attributeName) {
        AttributeName = attributeName;
    }
}
