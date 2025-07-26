package com.github.nothiaki.trtl3_core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.github.nothiaki.trtl3_core.shared.logger.Logger;

import jakarta.annotation.PostConstruct;

@Component
public class CoreConfig {

  private final Logger logger;

  public CoreConfig(Logger logger) {
    this.logger = logger;
  }

  @Value("${core.token}")
  private String token;

  private String rootDir = "/tmp/buckets/";

  @PostConstruct
  public void logInitialization() {
    logger.info(
      CoreConfig.class,
      "TOKEN as: {} ROOT DIR as: {}", token, rootDir
    );
  }

  public String getToken() {
    return token;
  }

  public String getRootDir() {
    return rootDir;
  }

}

