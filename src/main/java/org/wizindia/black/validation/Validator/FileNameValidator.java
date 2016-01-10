package org.wizindia.black.validation.validator;

import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.ValidationErrorCode;
import org.wizindia.black.validation.ValidationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nischal.k on 07/12/15.
 */
public class FileNameValidator implements Validator {

    int  fileNameSize = Configs.FileNameMaxLength;

    @Override
    public List<? extends ValidationError> validate(Object object) {
        String fileName = (String) object;
        if(fileName.length() > fileNameSize)
            return Arrays.asList(ValidationErrorCode.FileNameMoreThanExpected);
        return new ArrayList<>();
    }
}
