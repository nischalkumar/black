package org.wizindia.black.validation.validator;

import org.wizindia.black.common.Enums.Role;
import org.wizindia.black.common.Enums.ValidationErrorCode;
import org.wizindia.black.validation.PolicyValidatorContext;
import org.wizindia.black.validation.ValidationError;
import org.wizindia.black.validation.validator.Validator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hari_om on 27/9/15.
 */
public class PolicyValidator implements Validator {
    @Override
    public List<? extends ValidationError> validate(Object object) {
        List<ValidationErrorCode> validationErrorCodeList = new ArrayList<>();
        PolicyValidatorContext policyValidatorContext = (PolicyValidatorContext) object;
        if(policyValidatorContext.getUser().getRoles().contains(Role.ADMIN))
            return new ArrayList<>();
        for( Role role: policyValidatorContext.getRoleList()) {
            if(!policyValidatorContext.getUser().getRolesSet().contains(role)) {
                validationErrorCodeList.add(ValidationErrorCode.InvalidRole);
            }
        }
        return validationErrorCodeList;
    }
}
