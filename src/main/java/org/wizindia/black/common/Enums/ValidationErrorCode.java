package org.wizindia.black.common.Enums;

import org.wizindia.black.validation.ValidationError;

/**
 * Created by nischal.k on 07/12/15.
 */
public enum ValidationErrorCode implements ValidationError{
    FileNameMoreThanExpected(100, "File Name has more than allowed characters"),
    FileSizeMoreThanExpected(101, "File size more than allowed size"),
    FileSizeLessThanExpected(102, "File size less than allowed size"),
    FileExtensionNotSupported(103, "File extension is not supported");

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
