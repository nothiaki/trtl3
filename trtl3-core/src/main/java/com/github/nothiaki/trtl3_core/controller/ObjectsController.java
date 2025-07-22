package com.github.nothiaki.trtl3_core.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.nothiaki.trtl3_core.service.ObjectService;


@RestController
@RequestMapping("/objects")
public class ObjectsController {

  private final ObjectService objectService;

  public ObjectsController(ObjectService objectService) {
    this.objectService = objectService;
  }

  @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> createObject(
    @RequestPart(value = "object") MultipartFile object,
    @RequestParam("object-name") String objectName,
    @RequestParam("bucket") String bucketName
  ) {
    objectService.createObject(object, objectName, bucketName);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping()
  public ResponseEntity<Void> removeObject(
    @RequestParam("object-name") String objectName,
    @RequestParam("bucket") String bucketName
  ) {
    objectService.removeObject(objectName, bucketName);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @GetMapping
  public ResponseEntity<List<String>> listObjects(
    @RequestParam("bucket") String bucketName
  ) {
    List<String> objectsNames = objectService.listObjects(bucketName);
    return ResponseEntity.status(HttpStatus.OK).body(objectsNames);
  }

}
