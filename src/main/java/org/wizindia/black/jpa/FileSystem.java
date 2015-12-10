package org.wizindia.black.jpa;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by nischal.k on 07/12/15.
 */
public interface FileSystem {
    /*
    saves MultipartFile at the give location.
    if the path does not exist, The path is created. and the file is saved.
     */
    String save (String path, MultipartFile file) throws IOException,NullPointerException,UnsupportedOperationException;

    /*
    returns List of MultiPart File at the path specified.
    if the path is folder then it returns all the files, else returns only one file.
    Never returns a null. A checked exception must be thrown if nothing exists at the path specified.
     */
    List<File> get(String path, boolean isOnlyFileNameRequired) throws IOException,NullPointerException;

    /*
    deletes file at the current path.
    Returns no of file deleted at the current path.
    Never returns a null. A checked exception must be thrown if nothing exists at the path specified.
     */
    int delete(String path);

    /*
    Renames the particular file.
    Returns boolean value indicating the process was successfully completed or not.
    Never returns a null. A checked exception must be thrown if nothing exists at the path specified.
     */
    boolean rename(String path);

    /*
    move the file at current path to destination. you can not move entire folder at once.
    Returns boolean value indicating the process was successfully completed or not.
    Never returns a null. A checked exception must be thrown if nothing exists at the path specified.
     */
    boolean move(String currentPath, String destination) throws Exception ;

    String getFileSavePath(String context, String fileName);

    String getDownloadLink(String fileName);
}
