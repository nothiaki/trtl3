package com.github.nothiaki.trtl3_core.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.nothiaki.trtl3_core.exceptions.AbsentBucketsException;
import com.github.nothiaki.trtl3_core.exceptions.InternalErrorException;

@Service
public class BucketService {

  private String bucketsRootDir = "./buckets/";

  public boolean createBucket(String bucketName) {
    try {
      File newDirectory = new File(bucketsRootDir + bucketName);

      return newDirectory.mkdirs();
    } catch (Exception e) {
      throw new InternalErrorException();
    }
  }

  public List<String> findBuckets() {
    try {
      File bucketsDirectory = new File(bucketsRootDir);
      File[] buckets = bucketsDirectory.listFiles(File::isDirectory);


      if (!bucketsDirectory.exists()) {
        throw new AbsentBucketsException();
      }

      List<String> bucketsNames = new ArrayList<>();

      for(File dir : buckets) {
        bucketsNames.add(dir.getName());
      }

      return bucketsNames;
    } catch (Exception e) {
        throw new InternalErrorException();
    }
  }

}
