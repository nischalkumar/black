package org.wizindia.black.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by nischal.k on 07/12/15.
 */
public class Configs {
    public static final int FileNameMaxLength = 20;

    //in bytes, i.e. 10MB right now
    public static final int FileMaxSize = 1024*1024*10;

    //in bytes //20 bytes now
    public static final int FileMinSize = 20;

    public static final List<String> allowedFileExtension= new ArrayList<>(Arrays.asList("jpg", "jpeg", "doc", "docx", "png", "pdf"));

    public static final int TIMEOUT = 500;

    public static final String filePathSeparator = ".";

    //Separator must be present at the end
    public static final String FileSystem = "LOCAL_FILE_SYSTEM";
    public static final String primaryPath = "/var/wizindia/";
    public static final String baseUrl = "192.168.1.107:8444";
}
