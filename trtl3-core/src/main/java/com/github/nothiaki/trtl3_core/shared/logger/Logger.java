package com.github.nothiaki.trtl3_core.shared.logger;

public interface Logger {

  public void info(Class<?> clazz, String message, Object... args);
  public void error(Class<?> clazz, String message, Object... args);

}
