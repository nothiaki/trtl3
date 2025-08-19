import type { AxiosInstance } from 'axios';

export class BucketApi {
	constructor(private client: AxiosInstance) {}

	async create(bucketName: string): Promise<boolean> {
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

	async list(): Promise<string[]> {
		try {
			const res = await this.client.get(`/buckets`);

			if (res.status != 200) {
				return [];
			}

			return res.data;
		} catch (err: unknown) {
			return [];
		}
	}

	async remove(bucketName: string): Promise<boolean> {
		try {
			const res = await this.client.delete(`/buckets?bucket=${bucketName}`);

			if (res.status != 200) {
				return false;
			}

			return true;
		} catch (err: unknown) {
			return false;
		}
	}
}
