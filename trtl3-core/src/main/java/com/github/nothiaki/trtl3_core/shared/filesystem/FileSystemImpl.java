package com.github.nothiaki.trtl3_core.shared.filesystem;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class FileSystemImpl implements FileSystem {

  @Override
  public boolean createDirectory(String path) {
    return true;
  }

  @Override
  public File findDirectory(String path) {
    return null;
  }
}
