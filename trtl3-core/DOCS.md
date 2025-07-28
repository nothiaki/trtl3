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
response -- 2XX -- empty

---

list buckets -- GET<br />
request -- empty<br />
response -- 2XX
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
/upload?bucket=new-bucket&object-name=file.png<br />

MULTIPART-FORM:
| key | value |
| --- | ----- |
| object | `file` |

response -- 2XX -- empty

---

delete an object -- DELETE<br />
request<br />
?bucket=new-bucket&object-name=file.png<br />
response -- 2XX -- empty

---

find objects -- GET<br />
request<br />
?bucket=new-bucket<br />
response -- 2XX
```JSON
[
  "file.png"
]
```

---

download object -- GET<br />
request<br />
/download?bucket=new-bucket&object-name=file.png<br />

response -- 2XX

| name | value |
| --------------- | --------------- |
| Content-Type | application/octet-stream |
| Content-Disposition | attachment; filename="file.png" |

```
@@@@!!!**binaryfile
```

---
