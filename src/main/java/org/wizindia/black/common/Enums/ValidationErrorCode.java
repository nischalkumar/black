package org.wizindia.black.common.Enums;

import org.wizindia.black.validation.ValidationError;

/**
 * Created by nischal.k on 07/12/15.
 */
public enum ValidationErrorCode implements ValidationError{
    FileNameMoreThanExpected(100, "Feed Name has more than allowed characters"),
    FileSizeMoreThanExpected(101, "Feed size more than allowed size"),
    FileSizeLessThanExpected(102, "Feed size less than allowed size"),
    FileExtensionNotSupported(103, "Feed extension is not supported"),
    InvalidRole(200, "Unauthorized access");

    private int code;
    private String message;

    private ValidationErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
