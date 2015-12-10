package org.wizindia.black.jpa;

import org.springframework.web.multipart.MultipartFile;
import org.wizindia.black.common.Configs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.*;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by nischal.k on 07/12/15.
 */
public class LocalFileSystem implements FileSystem {

    public final static String PERMISSION_READ_WRITE = "rw";
    @Override
    public String save(String path, MultipartFile file) throws IOException,NullPointerException,UnsupportedOperationException {
        Path pathToFile = Paths.get(path);
        Files.createDirectories(pathToFile.getParent());
        File convFile = Files.createFile(pathToFile).toFile();
        FileChannel channel = new RandomAccessFile(convFile, PERMISSION_READ_WRITE).getChannel();
        FileLock lock = channel.lock();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        lock.release();
        channel.close();
        fos.close();
        return path;
    }

    @Override
    public List<File> get(String path, boolean isOnlyFileNameRequired) throws IOException,NullPointerException {
        List<File> fileArray = new ArrayList<>();
        Path filePath = Paths.get(path);
        File file = Files.createFile(filePath).toFile();
        if (file.isFile()) {
            fileArray.add(file);
        }else {
            for (File tempFile: file.listFiles()){
                if (tempFile.isFile()) fileArray.add(tempFile);
            }
        }
        return fileArray;
    }

    @Override
    public int delete(String path) {
        File file = new File(path);
        return file.delete()?1:0;  // 1 if delted 0 if not deleted
    }

    @Override
    public boolean rename(String path) {
        return false;
    }

    @Override
    public boolean move(String currentPath, String destination) throws IOException {
        Path movefrom = FileSystems.getDefault().getPath(currentPath);
        Path target = FileSystems.getDefault().getPath(destination);
        Files.move(movefrom, target, StandardCopyOption.REPLACE_EXISTING);
        return true;
    }

    @Override
    public String getFileSavePath(String context, String filePath) {
        return Configs.primaryPath + context.replace(",", "/") + filePath;
    }

    @Override
    public String getDownloadLink(String fileName) {
        return null;
    }
}
