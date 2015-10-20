package org.wizindia.black.validation;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hari_om on 12/10/15.
 */
public class ValidatorContextMapBuilder {

    Map<ValidatorEnum, Object> validatorContextMap= new HashMap<>();

    public ValidatorContextMapBuilder() {
    }

    public ValidatorContextMapBuilder(Map<ValidatorEnum, Object> validatorContextMap) {
        this.validatorContextMap = validatorContextMap;
    }

    public ValidatorContextMapBuilder addValidator(ValidatorEnum validatorEnum, Object object) {
        validatorContextMap.put(validatorEnum, object);
        return this;
    }

    public Map<ValidatorEnum, Object> build() {
        return this.validatorContextMap;
    }
}
