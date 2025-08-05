import axios, { AxiosInstance } from "axios";

class Trtl3Client {

  private client: AxiosInstance;

  constructor(url: string, token: string) {
    this.client = axios.create({
      url,
      timeout: 5000,
      headers: {
        'Authorization': token,
      },
    });
  }

  async createBucket(bucketName: string): Promise<any> {
    try {
      const res = await this.client.post(`/buckets`, { name: bucketName });
      return res.data;
    } catch (err: unknown) {
      throw new Error(`Failed when try to create a bucket: ${err}`);
    }
  }

}

export class trtl3sdk {

  static create(url: string, token: string): Trtl3Client {
    return new Trtl3Client(url, token);
  }

}
