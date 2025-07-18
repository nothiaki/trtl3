/buckets

create a bucket
POST
{
  "bucketName": "bk"
}
2XX

list buckets
GET
2XX

/objects

create a object
note that you should create a bucket before
?bucket=bk&object-name=file.jpg
MULTIPART-FORM:
key: object file: any file least 15MB
2XX
