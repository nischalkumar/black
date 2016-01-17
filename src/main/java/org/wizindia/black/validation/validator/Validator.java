package org.wizindia.black.validation.validator;

import org.wizindia.black.validation.ValidationError;

import java.util.List;

/**
 * Created by hari_om on 27/9/15.
 */
public interface Validator {
    public List<? extends ValidationError> validate(Object object);
}
