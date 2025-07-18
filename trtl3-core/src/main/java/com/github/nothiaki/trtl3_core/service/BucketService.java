package com.github.nothiaki.trtl3_core.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.github.nothiaki.trtl3_core.shared.exceptions.AbsentBucketsException;
import com.github.nothiaki.trtl3_core.shared.exceptions.InternalErrorException;
import com.github.nothiaki.trtl3_core.shared.filesystem.FileSystem;
import com.github.nothiaki.trtl3_core.shared.logger.Logger;

@Service
public class BucketService {

  private final Logger logger;
  private final FileSystem fileSystem;

  public BucketService(
    Logger logger,
    FileSystem fileSystem
  ) {
    this.logger = logger;
    this.fileSystem = fileSystem;
  }

  private String bucketsRootDir = "/tmp/buckets/";

  public boolean createBucket(String bucketName) {
    try {
      boolean created = fileSystem.createDirectory(bucketsRootDir + bucketName);

      if(!created) {
        logger.info(BucketService.class, "Could not create bucket with name {}", bucketName);
        throw new InternalErrorException();
      }

      logger.info(BucketService.class, "Bucket with name {} was created", bucketName);
      return created;
    } catch (Exception e) {
      logger.info(BucketService.class, "Could not create bucket with name {} was created", bucketName, e);
      throw new InternalErrorException();
    }
  }

  public List<String> findBuckets() {
    try {
      File bucketsDirectory = fileSystem.findDirectory(bucketsRootDir);
      File[] buckets = bucketsDirectory.listFiles(File::isDirectory);

      if (!bucketsDirectory.exists()) {
        logger.info(BucketService.class, "No buckets found in directory at path: {}", bucketsRootDir);
        throw new AbsentBucketsException();
      }

      List<String> bucketsNames = new ArrayList<>();

      for(File dir : buckets) {
        bucketsNames.add(dir.getName());
      }

      logger.info(BucketService.class, "Successfully found {} buckets", bucketsNames.size());
      return bucketsNames;
    } catch (Exception e) {
      logger.info(BucketService.class, "Could not find created buckets", e);
      throw new InternalErrorException();
    }
  }

}
