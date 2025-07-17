package com.github.nothiaki.trtl3_core.shared.filesystem;

import java.io.File;

public interface FileSystem {

  public boolean createDirectory(String path);
  public File findDirectory(String path);

}
