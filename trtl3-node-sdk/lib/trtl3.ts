import axios, { type AxiosInstance } from "axios";
import { BucketApi } from "./buckets.js";

class Trtl3ClientImpl {

  private client: AxiosInstance;
  public bucket: BucketApi;

  constructor(url: string, token: string) {
    this.client = axios.create({
      baseURL: url,
      timeout: 5000,
      headers: {
        'Authorization': token,
      },
    });

    this.bucket = new BucketApi(this.client);
  }

}
 
export type Trtl3Client = InstanceType<typeof Trtl3ClientImpl>;

export class trtl3sdk {

  static init(url: string, token: string): Trtl3Client {
    return new Trtl3ClientImpl(url, token);
  }

}
