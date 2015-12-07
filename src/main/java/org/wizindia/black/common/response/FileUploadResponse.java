package org.wizindia.black.common.response;

/**
 * Created by nischal.k on 07/12/15.
 */
public class FileUploadResponse {
    private String id;
    private String path;

    public FileUploadResponse(String id, String path) {
        this.id = id;
        this.path = path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
