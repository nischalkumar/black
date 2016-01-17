package org.wizindia.black.common.Exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wizindia.black.validation.ValidationError;

import java.util.List;

/**
 * Created by hari_om on 17/1/16.
 */
public class ValidationRuntimeException extends RuntimeException {

    private String errorCode;
    private String errorDescription;
    private static final String separator = " || ";

    public ValidationRuntimeException(List<ValidationError> validationErrors) {
        super();
        errorCode = "400";
        StringBuilder stringBuilder = new StringBuilder();
        for(ValidationError validationError : validationErrors) {
            stringBuilder.append(validationError.getMessage()).append(separator);
        }

        errorDescription = stringBuilder.substring(0,stringBuilder.lastIndexOf(separator)).toString();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }
}
