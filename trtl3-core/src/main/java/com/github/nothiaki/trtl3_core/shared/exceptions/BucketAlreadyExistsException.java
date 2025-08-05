package com.github.nothiaki.trtl3_core.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class BucketAlreadyExistsException extends RuntimeException {

  public BucketAlreadyExistsException() {
    super("Bucket already exists");
  }

}
