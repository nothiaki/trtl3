package com.github.nothiaki.trtl3_core.shared.filesystem;

import java.io.File;

import org.springframework.stereotype.Service;

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

}
