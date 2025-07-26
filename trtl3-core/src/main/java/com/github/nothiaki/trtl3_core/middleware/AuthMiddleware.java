package com.github.nothiaki.trtl3_core.middleware;

import java.io.IOException;

import com.github.nothiaki.trtl3_core.config.CoreConfig;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@jakarta.servlet.annotation.WebFilter(
  filterName = "AUTH_FILTER", urlPatterns = "*"
)
public class AuthMiddleware implements Filter {

  private final CoreConfig coreConfig;

  public AuthMiddleware(CoreConfig coreConfig) {
    this.coreConfig = coreConfig;
  }

  @Override
  public void doFilter(
    ServletRequest request,
    ServletResponse response,
    FilterChain chain
  )
    throws IOException, ServletException {
      HttpServletRequest httpRequest = (HttpServletRequest) request;

      String token = httpRequest.getHeader("Authorization");

      if (token == null) {
        ((HttpServletResponse) response)
          .sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing");
        return;
      }

      if (!token.equals(coreConfig.getToken())) {
        ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid Token");
        return;
      }

      chain.doFilter(request, response);
  }

}
