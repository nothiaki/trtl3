package com.github.nothiaki.trtl3_core.service;

import java.io.File;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.nothiaki.trtl3_core.shared.exceptions.AbsentBucketsException;
import com.github.nothiaki.trtl3_core.shared.exceptions.AbsentObjectException;
import com.github.nothiaki.trtl3_core.shared.exceptions.InternalErrorException;
import com.github.nothiaki.trtl3_core.shared.filesystem.FileSystem;
import com.github.nothiaki.trtl3_core.shared.logger.Logger;

@Service
public class ObjectService {

  private final Logger logger;
  private final FileSystem fileSystem;

  public ObjectService(
    Logger logger,
    FileSystem fileSystem
  ) {
    this.logger = logger;
    this.fileSystem = fileSystem;
  }

  private String bucketsRootDir = "/tmp/buckets/";

  public boolean createObject(
    MultipartFile object,
    String objectName,
    String bucketName
  ) {
    try {
      File bucket = fileSystem.findDirectory(bucketsRootDir + bucketName);

      if (bucket == null) {
        throw new AbsentBucketsException();
      }

      object.transferTo(fileSystem.createFile(bucket, objectName));

      logger.info(ObjectService.class, "Object {} saved in bucket {}", objectName, bucketName);
      return true;
    } catch (Exception e) {
      logger.error(
        ObjectService.class,
        "Failed to save object {} in bucket {}: {}", objectName, bucketName, e
      );
      throw new InternalErrorException();
    }
  }

  public boolean removeObject(
    String objectName,
    String bucketName
  ) {
    try {
      File object = fileSystem.findFile(bucketsRootDir + bucketName + "/" + objectName);

      if (object == null) {
        throw new AbsentObjectException();
      }

      object.delete();

      logger.info(ObjectService.class, "Object {} saved in bucket {} deleted with success", objectName, bucketName);
      return true;
    } catch (Exception e) {
      logger.error(
        ObjectService.class,
        "Failed to delete object {} in bucket {}: {}", objectName, bucketName, e
      );
      throw new InternalErrorException();
    }
  }

}
