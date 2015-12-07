package org.wizindia.black.validation;

import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.ValidationErrorCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nischal.k on 07/12/15.
 */
public class FileExtensionValidator implements Validator {
    List<String> allowedExtensions = Configs.allowedFileExtension;
    @Override
    public List<? extends ValidationError> validate(Object object) {
        String extension = ((String)object).split(".")[1];
        if(allowedExtensions.contains(extension))
            return Arrays.asList(ValidationErrorCode.FileExtensionNotSupported);
        return new ArrayList<>();
    }
}
