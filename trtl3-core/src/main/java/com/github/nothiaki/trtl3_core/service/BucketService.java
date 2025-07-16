package com.github.nothiaki.trtl3_core.service;

import org.springframework.stereotype.Service;

import com.github.nothiaki.trtl3_core.dto.bucket.RequestCreateBucketDto;

@Service
public class BucketService {

  public boolean createBucket(RequestCreateBucketDto requestCreateBucketDto) {
    return true;
  }

}
