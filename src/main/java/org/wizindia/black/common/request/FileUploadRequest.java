package org.wizindia.black.common.request;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * Created by nischal.k on 07/12/15.
 */
public class FileUploadRequest {

    private String fileName;
    private MultipartFile file;
    private String context;

    public FileUploadRequest(String fileName, MultipartFile file) {
        this.fileName = fileName;
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public MultipartFile getFile() {
        return file;
    }

    public String getContext() {
        return context;
    }
}
