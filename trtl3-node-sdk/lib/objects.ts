import type { AxiosInstance } from "axios";

export class ObjectApi {

  constructor(private client: AxiosInstance) {}

  async list(
    bucketName: string,
  ): Promise<string[]> {
    try {
      const res = await this.client.get(`/objects?bucket=${bucketName}`);

      if (res.status != 200) {
        return [];
      }

      return res.data;
    } catch (err: unknown) {
      return [];
    }
  }

  async remove(
    bucketName: string,
    objectName: string,
  ): Promise<boolean> {
    try {
      const res = await this.client.delete(
        `/objects?bucket=${bucketName}&object=${objectName}`,
      );

      if (res.status != 200) {
        return false;
      }

      return true;
    } catch (err: unknown) {
      return false;
    }
  }

}

