package org.wizindia.black.common.response;

import org.codehaus.jackson.annotate.JsonProperty;
import org.wizindia.black.common.Exception.ValidationRuntimeException;

/**
 * Created by hari_om on 17/1/16.
 */
public class ValidationErrorResponse {
    @JsonProperty("code")
    String code;

    @JsonProperty("description")
    String description;

    public ValidationErrorResponse(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public ValidationErrorResponse(ValidationRuntimeException validationRuntimeException) {
        this.code = validationRuntimeException.getErrorCode();
        this.description = validationRuntimeException.getErrorDescription();
    }
}
