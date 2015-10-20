package org.wizindia.black.validation;

import java.util.List;

/**
 * Created by hari_om on 27/9/15.
 */
public class PolicyValidator implements Validator {
    @Override
    public List<? extends ValidationError> validate(Object object) {
        PolicyValidatorContext policyValidatorContext = (PolicyValidatorContext) object;
        return null;
    }
}
