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

delete an object
note that you should create a bucket and an object before
?bucket=bk&object-name=file.jpg
2XX

find objects
?bucket=bk
2XX
