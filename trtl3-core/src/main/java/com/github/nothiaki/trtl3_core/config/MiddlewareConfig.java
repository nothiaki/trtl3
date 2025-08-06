package com.github.nothiaki.trtl3_core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.nothiaki.trtl3_core.middleware.AuthMiddleware;

@Configuration
public class MiddlewareConfig {
  
  private final CoreConfig coreConfig;

  public MiddlewareConfig(CoreConfig coreConfig) {
    this.coreConfig = coreConfig;
  }

  @Bean
  public FilterRegistrationBean<AuthMiddleware> authMiddleware() {
    FilterRegistrationBean<AuthMiddleware> bean = new FilterRegistrationBean<>();
    bean.setFilter(new AuthMiddleware(coreConfig));
    bean.addUrlPatterns("/buckets/*", "/objects/*");
    return bean;
  }

}
