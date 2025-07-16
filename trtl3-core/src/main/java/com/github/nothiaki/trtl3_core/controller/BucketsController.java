package com.github.nothiaki.trtl3_core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.nothiaki.trtl3_core.dto.bucket.RequestCreateBucketDto;
import com.github.nothiaki.trtl3_core.service.BucketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bucket")
public class BucketsController {

  private final BucketService bucketService;

  public BucketsController(BucketService bucketService) {
    this.bucketService = bucketService;
  }

  @PostMapping
  public ResponseEntity<?> createBucket(@RequestBody @Valid RequestCreateBucketDto requestCreateBucketDto) {
    return bucketService.createBucket(requestCreateBucketDto) ?
      ResponseEntity.status(HttpStatus.CREATED).build() :
      ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
  }

}
