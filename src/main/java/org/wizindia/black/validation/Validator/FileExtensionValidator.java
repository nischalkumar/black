package org.wizindia.black.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.ValidationErrorCode;
import org.wizindia.black.utils.FileSystemUtils;
import org.wizindia.black.validation.ValidationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nischal.k on 07/12/15.
 */
public class FileExtensionValidator implements Validator {
    List<String> allowedExtensions = Configs.allowedFileExtension;

    @Autowired
    FileSystemUtils fileSystemUtils;

    @Override
    public List<? extends ValidationError> validate(Object object) {
        String extension = fileSystemUtils.getFileExtension((String) object);
        if(allowedExtensions.contains(extension))
            return Arrays.asList(ValidationErrorCode.FileExtensionNotSupported);
        return new ArrayList<>();
    }
}
