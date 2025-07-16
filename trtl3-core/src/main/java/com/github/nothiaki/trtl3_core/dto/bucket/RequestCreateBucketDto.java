package com.github.nothiaki.trtl3_core.dto.bucket;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record RequestCreateBucketDto(

  @NotEmpty(message = "Bucket name should not be empty")
  @Size(min = 1, max = 24)
  String bucketName

) {}
