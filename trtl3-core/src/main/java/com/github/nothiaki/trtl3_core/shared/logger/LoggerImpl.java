package com.github.nothiaki.trtl3_core.shared.logger;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggerImpl implements Logger {

  @Override
  public void info(Class<?> clazz, String message, Object... args) {
    LoggerFactory.getLogger(clazz).info(message, args);
  }

  @Override
  public void error(Class<?> clazz, String message, Object... args) {
    LoggerFactory.getLogger(clazz).error(message, args);
  }

}
