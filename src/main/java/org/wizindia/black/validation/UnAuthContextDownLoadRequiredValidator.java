package org.wizindia.black.validation;

import org.wizindia.black.common.Enums.ValidationErrorCode;
import org.wizindia.black.domain.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nischal.k on 10/01/16.
 */
public class UnAuthContextDownLoadRequiredValidator implements Validator {
    @Override
    public List<? extends ValidationError> validate(Object object) {
        Context context = (Context)object;
        if (!context.isAuthRequired())
            return Arrays.asList(ValidationErrorCode.InvalidAccess);
        return new ArrayList<>();
    }
}
