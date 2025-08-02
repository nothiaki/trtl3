> [!IMPORTANT]
> all routes needs a Authorization header with the token

### /buckets

---

create a bucket -- POST<br />
request
```JSON
{
  "bucketName": "new-bucket"
}
```
response -- 201 -- empty

---

list buckets -- GET<br />
request -- empty<br />
response -- 200
```JSON
[
  "new-bucket"
]
```

---
---
---

### /objects

---

upload object -- POST<br />
request<br />
/upload?bucket=new-bucket&object=file.png<br />

MULTIPART-FORM:
| key | value |
| --- | ----- |
| content | `file-to-upload.png` |

response -- 201 -- empty

---

delete an object -- DELETE<br />
request<br />
?bucket=new-bucket&object=file.png<br />
response -- 200 -- empty

---

find objects -- GET<br />
request<br />
?bucket=new-bucket<br />
response -- 200
```JSON
[
  "file.png"
]
```

---

download object -- GET<br />
request<br />
/download?bucket=new-bucket&object=file.png<br />

response -- 200

| name | value |
| --------------- | --------------- |
| Content-Type | application/octet-stream |
| Content-Disposition | attachment; filename="file.png" |

```
@@@@!!!**binaryfile
```

---
