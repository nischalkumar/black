package org.wizindia.black.common.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by nischal.k on 26/12/15.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ContextRequest {
    @JsonProperty("context_id")
    private String  contextId;

    @JsonProperty("folder_path")
    private String folderPath;

    @JsonProperty("max_file_size")
    private long maxFileSize;

    @JsonProperty("min_file_size")
    private long minFileSize;

    @JsonProperty("allowed_extension")
    private String allowedExtensions;

    @JsonProperty("is_auth_required")
    private boolean isAuthRequired;

    public ContextRequest() {
    }

    public ContextRequest(String contextId, String folderPath, long maxFileSize, long minFileSize, String allowedExtensions, boolean isAuthRequired) {
        this.contextId = contextId;
        this.folderPath = folderPath;
        this.maxFileSize = maxFileSize;
        this.minFileSize = minFileSize;
        this.allowedExtensions = allowedExtensions;
        this.isAuthRequired = isAuthRequired;
    }

    public ContextRequest(String folderPath, long maxFileSize, long minFileSize, String allowedExtensions, boolean isAuthRequired) {
        this.folderPath = folderPath;
        this.maxFileSize = maxFileSize;
        this.minFileSize = minFileSize;
        this.allowedExtensions = allowedExtensions;
        this.isAuthRequired = isAuthRequired;
    }

    public String getContextId() {
        return contextId;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public long getMaxFileSize() {
        return maxFileSize;
    }

    public long getMinFileSize() {
        return minFileSize;
    }

    public String getAllowedExtensions() {
        return allowedExtensions;
    }

    public boolean isAuthRequired() {
        return isAuthRequired;
    }
}
