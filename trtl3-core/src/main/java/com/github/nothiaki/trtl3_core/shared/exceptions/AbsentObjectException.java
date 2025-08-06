package com.github.nothiaki.trtl3_core.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AbsentObjectException extends RuntimeException {

  public AbsentObjectException() {
    super("Could not find this object");
  }

}
