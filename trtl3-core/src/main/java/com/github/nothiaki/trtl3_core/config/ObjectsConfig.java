package com.github.nothiaki.trtl3_core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ObjectsConfig implements WebMvcConfigurer {

  private final CoreConfig coreConfig;

  public ObjectsConfig(CoreConfig coreConfig) {
    this.coreConfig = coreConfig;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
    resourceHandlerRegistry
      .addResourceHandler("/public/**")
      .addResourceLocations("file:" + coreConfig.getRootDir())
      .setCachePeriod(1800);
  }

}

