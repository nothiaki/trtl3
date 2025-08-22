# Golang SDK

Here you can find our Golang SDK (Software Development Kit) — a library designed to make integrating Trtl3 into your project simple and seamless.

> Note: You can check an example project in using this SDK [here](https://github.com/nothiaki/trtl3/tree/main/examples/go-example)

## Initialize SDK Instance

To start to use our Golang SDK you must run `go get	github.com/nothiaki/trtl3/trtl3-golang-sdk` in your project directory.
After installed, simply follow the usage example below.

```go
import (
  "github.com/nothiaki/trtl3/trtl3-golang-sdk"
)

func main() {

  trtl3 := trtl3sdk.Init(
    "http://localhost:7713", // your running trtl3 server url
    "trtl3",                 // trtl3 access token
  )

}
```

## Create Bucket

```go
created, err := trtl3.CreateBucket("bucket-name")
if err != nil {
  fmt.Printf("Error while creating bucket %v", err)
  return
}

fmt.Println("bucket created ", created)
```

## List Buckets

```go
buckets, err := trtl3.ListBuckets()
if err != nil {
  fmt.Printf("Error while listing buckets %v", err)
  return
}

fmt.Println("buckets ", buckets)
```

## Delete Bucket

```go
deleted, err := trtl3.DeleteBucket("created-bucket")
if err != nil {
  fmt.Printf("error trying to delete bucket with name %v", err)
  return
}

fmt.Println("Deleted bucket", deleted)
```

## Upload Object

You can use the path of the current object or an file, look!

```go
// by path
ok, err := trtl3.UploadObjectByPath(
  "created-bucket",
  "/current/object/path.png",
  "new-object.png",
)

// by file
file, err := os.Open("/current/object/path.png")
if err != nil {
  fmt.Printf("Error while opening file: %v", err)
  return
}
defer file.Close()

ok, err := trtl3.UploadObject(
  "created-bucket",
  file,
  "new-object.png",
)
if err != nil {
  fmt.Printf("Error while uploading object %v", err)
  return
}

fmt.Println("uploaded object: ", ok)
```

## List Objects

```go
objects, err := trtl3.ListObjects("created-bucket")
if err != nil {
  fmt.Printf("Error while listing objects %v", err)
  return
}

fmt.Println("objects: ", objects)
```

## Delete Object

```go
deleted, err := trtl3.DeleteObject("created-bucket", "created-object.png")
if err != nil {
  fmt.Printf("Error while try to delete object %v", err)
  return
}

fmt.Println("deleted: ", deleted)
```

## Download Object

```go
downloaded, err := trtl3.DownloadObject("created-bucket", "created-object.png")
if err != nil {
  fmt.Printf("Error while try to download object %v", err)
  return	
}

// then you can save
p, err := os.Create("./file.png")
if err != nil {
  fmt.Printf("Could not create file: %v", err)
  return
}
defer p.Close()

_, err = io.Copy(p, downloaded)
if err != nil {
  fmt.Printf("Failed to save file: %v", err)
  return
}

fmt.Println("File saved")
```

