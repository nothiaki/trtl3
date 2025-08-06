package com.github.nothiaki.trtl3_core.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.nothiaki.trtl3_core.config.CoreConfig;
import com.github.nothiaki.trtl3_core.shared.exceptions.AbsentBucketsException;
import com.github.nothiaki.trtl3_core.shared.exceptions.AbsentObjectException;
import com.github.nothiaki.trtl3_core.shared.exceptions.InternalErrorException;
import com.github.nothiaki.trtl3_core.shared.filesystem.FileSystem;
import com.github.nothiaki.trtl3_core.shared.logger.Logger;

@Service
public class ObjectService {

  private final Logger logger;
  private final FileSystem fileSystem;
  private final CoreConfig coreConfig;

  public ObjectService(
    Logger logger,
    FileSystem fileSystem,
    CoreConfig coreConfig
  ) {
    this.logger = logger;
    this.fileSystem = fileSystem;
    this.coreConfig = coreConfig;
  }

  public boolean uploadObject(
    MultipartFile object,
    String objectName,
    String bucketName
  ) {
    try {
      File bucket = fileSystem.findDirectory(coreConfig.getRootDir() + bucketName);

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
      File object = fileSystem.findFile(coreConfig.getRootDir() + bucketName + "/" + objectName);

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

  public List<String> findObjects(String bucketName) {
    List<String> bucketsNames = listObjects(bucketName);

    logger.info(
      BucketService.class,
      "Successfully found {} objects in bucket {}",
      bucketsNames.size(), bucketName
    );

    return bucketsNames;
  }

  private List<String> listObjects(String bucketName) {
    try {
      String dir = coreConfig.getRootDir() + bucketName;

      File bucketDirectory = fileSystem.findDirectory(dir);
      File[] objects = bucketDirectory.listFiles(File::isFile);

      if (objects == null) {
        return new ArrayList<>();
      }

      List<String> objectsNames = new ArrayList<>();

      for(File file : objects) {
        objectsNames.add(file.getName());
      }

      logger.info(BucketService.class, "Successfully found {} objects in bucket {}", objectsNames.size(), bucketName);
      return objectsNames;
    } catch (Exception e) {
      logger.info(BucketService.class, "Could not find the bucket " + bucketName, e);
      throw new InternalErrorException();
    }
  }

  public InputStreamResource downloadObject(
    String objectName,
    String bucketName
  ) {
    try {
      InputStreamResource resource =
        fileSystem.downloadResource(
          coreConfig.getRootDir() + bucketName + "/" + objectName
        );

      if (resource == null) {
        throw new AbsentObjectException();
      }

      logger.info(
        ObjectService.class,
        "Found object object {} saved in bucket {} with success",
        objectName,
        bucketName
      );

      return resource;
    } catch (Exception e) {
      logger.error(
        ObjectService.class,
        "Could not find object {} saved in bucket {}: {}", objectName, bucketName, e
      );
      throw new InternalErrorException();
    }
  }

}
