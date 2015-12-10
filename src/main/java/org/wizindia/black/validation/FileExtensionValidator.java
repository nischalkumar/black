package org.wizindia.black.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileSystemUtils;
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

    @Autowired
    org.wizindia.black.utils.FileSystemUtils fileSystemUtils;

    @Override
    public List<? extends ValidationError> validate(Object object) {
        String extension = fileSystemUtils.getFileExtension((String) object);
        if(allowedExtensions.contains(extension))
            return Arrays.asList(ValidationErrorCode.FileExtensionNotSupported);
        return new ArrayList<>();
    }
}
