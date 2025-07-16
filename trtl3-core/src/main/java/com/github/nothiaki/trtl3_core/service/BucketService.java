package com.github.nothiaki.trtl3_core.service;

import java.io.File;

import org.springframework.stereotype.Service;

import com.github.nothiaki.trtl3_core.exceptions.InternalErrorException;

@Service
public class BucketService {

  public boolean createBucket(String bucketName) {
    try {
      File newDirectory = new File("./buckets/" + bucketName);

      return newDirectory.mkdirs();
    } catch (Exception e) {
      throw new InternalErrorException();
    }
  }

}
