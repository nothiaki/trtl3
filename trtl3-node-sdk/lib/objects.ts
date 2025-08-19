import type { AxiosInstance } from "axios";
import FormData from "form-data";

export class ObjectApi {

  constructor(private client: AxiosInstance) {}

  async upload(
    bucketName: string,
    objectName: string,
    data: Buffer,
  ): Promise<boolean> {
    try {
      const form = new FormData();

      form.append('content', data, { filename: objectName });

      const res = await this.client.post(
        `/objects/upload?bucket=${bucketName}&object=${objectName}`,
        form,
        {
          headers: form.getHeaders(),
        },
      );

      if (res.status !== 201) {
        return false;
      }

      return true;
    } catch (err: unknown) {
      return false;
    }
  }

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

  async download(
    bucketName: string,
    objectName: string,
  ): Promise<Buffer | null> {
    try {
      const res = await this.client.get(
        `/objects?bucket=${bucketName}&object=${objectName}`,
      );

      if (res.status !== 200) {
        return null;
      }

      return Buffer.from(res.data);
    } catch (err: unknown) {
      return null;
    }
  }

}

