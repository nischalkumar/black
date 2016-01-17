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
public class FileSizeValidator implements Validator {

    int  fileMaxSize = Configs.FileMaxSize;
    int  fileMinSize = Configs.FileMinSize;

    @Override
    public List<? extends ValidationError> validate(Object object) {
        long size = (long)object;
        if (size < fileMinSize)
            return Arrays.asList(ValidationErrorCode.FileSizeLessThanExpected);
        if (size > fileMaxSize)
            return Arrays.asList(ValidationErrorCode.FileSizeMoreThanExpected);
        return new ArrayList<>();

    }
}
