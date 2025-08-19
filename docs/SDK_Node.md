# Node SDK

Here you can find our Node SDK (Software Development Kit) — a library designed to make integrating Trtl3 into your project simple and seamless.

## Initialize SDK Instance

To start to use our Node SDK you must run `npm install trtl3-node-sdk` in your project directory.
After installed, simply follow the usage example below.

```typescript
import { trtl3sdk, type Trtl3Client } from './trtl3.js';

const trtl3: Trtl3Client = trtl3sdk.init(
  'http://localhost:7713/',  // your running trtl3 server url
  'trtl3',                   // trtl3 access token
);
```

## Create Bucket

```typescript
const created: boolean = await trtl3.bucket.create('new-bucket');

console.log(created);
```

## List Buckets

```typescript
const buckets: string[] = await trtl3.bucket.list();

console.log(buckets);
```

## Delete Bucket

```typescript
const deleted: boolean = await trtl3.bucket.remove('created-bucket');

console.log(deleted);
```

## Upload Object

You can use the path of the current object or an file, look!

```typescript
const data = fs.readFileSync("/your/fie/path.png");

const uploaded: boolean = await trtl3.object.upload(
  "my-bucket",
  "new-object.png",
  data,
);

console.log(uploaded);
```

## List Objects

```typescript
const objects: string[] = await trtl3.object.list('created-bucket');

console.log(objects);
```

## Delete Object

```typescript
const deleted: boolean = await trtl3.object.remove(
  'created-bucket',
  'created-object.png',
);

console.log(deleted);
```

## Download Object

```typescript
const bf: Buffer | null = await trtl3.object.download(
  'created-bucket',
  'created-object.png',
);

if (bf != null) {
  fs.writeFile('./new-file.png', bf, (err) => {
    if (err) {
      console.log('Error while saving file');
    } else {
      console.log('File saved');
    }
  });
} else {
  console.log('Buffer is null');
}
```
