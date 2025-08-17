# Endpoints

Below is the api reference if you want to use it.

## POST /buckets

desc: create a new bucket

REQUEST-BODY
```json
{
  "bucketName": "new-bucket"
}
```

## GET /buckets

desc: list created buckets

## DELETE /bucket?bucket=bucket-name

desc: delete bucket by name

## POST /objects/upload?bucket=bucket-name&object=new-object.png

desc: upload object

REQUEST-BODY: multipart-form
key: content
value: image.png

## GET /objects?bucket=bucket-name

desc: list objects by bucket

## DELETE /objects?bucket=bucket-name&object=object-name.png

desc: delete object by bucket

## GET /objects?bucket=bucket-name&object=object-name.png

desc: delete object by bucket

HEADERS:
Content-Disposition: attachment; filename=file.png
Content-Type: application/octet-stream

obs: you will got a bin file in response body
