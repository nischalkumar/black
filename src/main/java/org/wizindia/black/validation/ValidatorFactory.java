package org.wizindia.black.validation;

import org.wizindia.black.exception.ValidationNotFoundException;

import java.util.Map;

/**
 * Created by hari_om on 12/10/15.
 */
public class ValidatorFactory {
    Map<ValidatorEnum, Validator> validatorEnumValidatorMap;

    public ValidatorFactory(Map<ValidatorEnum, Validator> validatorEnumValidatorMap){
        this.validatorEnumValidatorMap = validatorEnumValidatorMap;
    }

    public Validator getValidator(ValidatorEnum validatorEnum) {
        Validator validator = validatorEnumValidatorMap.get(validatorEnum);
        if(validator == null)
            throw new ValidationNotFoundException();
        return validator;
    }
}
