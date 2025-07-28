package com.github.nothiaki.trtl3_core.shared.filesystem;

import java.io.File;

import org.springframework.core.io.InputStreamResource;

public interface FileSystem {

  public boolean createDirectory(String path);
  public File findDirectory(String path);
  public File findFile(String path);
  public File createFile(File parentDirectory, String fileName);
  public InputStreamResource downloadResource(String path);

}
