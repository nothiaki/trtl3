package com.github.nothiaki.trtl3_core.shared.filesystem;

import java.io.File;
import java.io.FileInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import com.github.nothiaki.trtl3_core.shared.exceptions.InternalErrorException;

@Service
public class FileSystemImpl implements FileSystem {

  @Override
  public boolean createDirectory(String path) {
    return new File(path).mkdirs();
  }

  @Override
  public File findDirectory(String path) {
    File directory = new File(path);

    if (!directory.exists() || !directory.isDirectory()) {
      return null;
    }

    return directory;
  }

  @Override
  public File createFile(File parentDirectory, String fileName) {
    return new File(parentDirectory, fileName);
  }

  @Override
  public File findFile(String path) {
    File file = new File(path);

    if (!file.exists() || !file.isFile()) {
      return null;
    }

    return file;
  }

  @Override
  public InputStreamResource downloadResource(String path) {
    File file = new File(path);

    if (!file.exists() || !file.isFile()) {
      return null;
    }

    try {
      return new InputStreamResource(new FileInputStream(file));
    } catch (Exception e) {
      throw new InternalErrorException();
    }
  }

  @Override
  public boolean deleteDirectory(String path) {
    try {
      File dir = findDirectory(path);

      deleteDirectoryRecursive(dir);
      return true;     
    } catch (Exception e) {
      return false;
    }
  }

  public void deleteDirectoryRecursive(File dir) {
    if (dir.isDirectory()) {
      File[] in = dir.listFiles();

      if (in != null) {
        for (File f : in) {
          deleteDirectoryRecursive(f);
        }
      }
    }

    dir.delete();
  }

}
