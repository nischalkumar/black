package org.wizindia.black.common;

/**
 * Created by nischal.k on 12/12/15.
 */
public class FinalFilePathContext {
    private String fileName;
    private String fileContext;

    public FinalFilePathContext(String fileName, String fileContext) {
        this.fileName = fileName;
        this.fileContext = fileContext;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileContext() {
        return fileContext;
    }
}
