# Install

After Install Docker you can install trtl3 using this command:

```bash
docker run -d --name trtl3-core -p 7273:7273 -e TOKEN=your_secret_token nothiaki/trtl3-core:latest
```

So the service will be running on `http://localhost:7273/` and you can use by REST or use SDK's.

If you want to use docker compose you can add it on the file:

```yaml
services:

  trtl3-core:
    image: nothiaki/trtl3-core:latest
    ports:
      - 7273:7273
    environment:
      - TOKEN=your_token_here
```
