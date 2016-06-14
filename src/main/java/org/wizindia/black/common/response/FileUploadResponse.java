package org.wizindia.black.common.response;

import lombok.ToString;

/**
 * Created by nischal.k on 07/12/15.
 */
@ToString
public class FileUploadResponse {
    private String path;

    public FileUploadResponse(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
