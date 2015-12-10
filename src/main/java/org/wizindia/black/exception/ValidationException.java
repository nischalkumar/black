package org.wizindia.black.exception;

import org.wizindia.black.validation.ValidationError;

import java.util.List;

/**
 * Created by nischal.k on 10/12/15.
 */
public class ValidationException extends RuntimeException{
    private String code;
    private String message;

    public ValidationException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public ValidationException(List<ValidationError> validationErrorList) {
        StringBuilder messageStringBuilder = new StringBuilder();
        StringBuilder codeStringBuilder = new StringBuilder();
        for(ValidationError validationError : validationErrorList) {
            messageStringBuilder.append(validationError.getMessage());
            messageStringBuilder.append(" || ");
            codeStringBuilder.append(validationError.getCode());
            codeStringBuilder.append(" || ");
        }
        this.message = messageStringBuilder.toString();
        this.code = codeStringBuilder.toString();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
