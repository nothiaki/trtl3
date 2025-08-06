package com.github.nothiaki.trtl3_core.shared.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AbsentBucketsException extends RuntimeException {

  public AbsentBucketsException() {
    super("There is no buckets created");
  }

}
