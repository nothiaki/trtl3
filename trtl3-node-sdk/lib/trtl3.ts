import axios, { type AxiosInstance } from "axios";

class Trtl3ClientImpl {

  private client: AxiosInstance;

  constructor(url: string, token: string) {
    this.client = axios.create({
      baseURL: url,
      timeout: 5000,
      headers: {
        'Authorization': token,
      },
    });
  }

  async createBucket(bucketName: string): Promise<boolean> {
    try {
      const res = await this.client.post(`/buckets`, { bucketName });

      if (res.status != 201) {
        return false;
      }

      return true;
    } catch (err: unknown) {
      return false;
    }
  }

}

export type Trtl3Client = InstanceType<typeof Trtl3ClientImpl>;

export class trtl3sdk {

  static init(url: string, token: string): Trtl3Client {
    return new Trtl3ClientImpl(url, token);
  }

}
