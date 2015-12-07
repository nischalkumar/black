package org.wizindia.black.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.wizindia.black.common.Configs;
import org.wizindia.black.common.Enums.FileSystemEnum;
import org.wizindia.black.jpa.FileSystem;
import org.wizindia.black.jpa.LocalFileSystem;

/**
 * Created by nischal.k on 07/12/15.
 */
public class FileSystemFactory {

    @Autowired
    LocalFileSystem localFileSystem;
    public FileSystem getFileSystem() {
        FileSystemEnum fileSystemEnum = FileSystemEnum.valueOf(Configs.FileSystem);
        if(fileSystemEnum.equals(FileSystemEnum.LOCAL_FILE_SYSTEM))
            return localFileSystem;
        return localFileSystem;
    }

}
