package com.github.nothiaki.trtl3_core.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ObjectAlreadyExistsException extends RuntimeException {

  public ObjectAlreadyExistsException() {
    super("Already exist an object with this name");
  }

}
