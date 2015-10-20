package org.wizindia.black.validation;

/**
 * Created by hari_om on 27/9/15.
 */
public class ValidationError {
    String message;
    int code;

    public ValidationError() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
