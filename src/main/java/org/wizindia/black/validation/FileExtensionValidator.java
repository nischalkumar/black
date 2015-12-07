package org.wizindia.black.validation;

import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.ValidationErrorCode;

import java.io.File;
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
        String extension = getFileExtension((String) object);
        if(allowedExtensions.contains(extension))
            return Arrays.asList(ValidationErrorCode.FileExtensionNotSupported);
        return new ArrayList<>();
    }

    private String getFileExtension(String name) {
        try {
            return name.substring(name.lastIndexOf(".") + 1);
        } catch (Exception e) {
            return "";
        }
    }
}
