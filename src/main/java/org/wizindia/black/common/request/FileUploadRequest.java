package org.wizindia.black.common.request;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by nischal.k on 07/12/15.
 */
public class FileUploadRequest {

    private String fileName;
    private String context;

    public FileUploadRequest(String fileName, String context) {
        this.fileName = fileName;
        this.context = context;
    }

    public String getFileName() {
        return fileName;
    }

    public String getContext() {
        return context;
    }
}
