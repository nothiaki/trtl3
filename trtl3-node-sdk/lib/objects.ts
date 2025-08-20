import type { AxiosInstance } from 'axios';
import FormData from 'form-data';
import * as fs from 'fs';

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

			const res = await this.client.post('/objects/upload', form, {
				params: {
					bucket: bucketName,
					object: objectName,
				},
				headers: form.getHeaders(),
			});

			if (res.status !== 201) {
				return false;
			}

			return true;
		} catch (err: unknown) {
			return false;
		}
	}

	async uploadByPath(
		bucketName: string,
		objectName: string,
		path: string,
	): Promise<boolean> {
		try {
			const data = fs.readFileSync(path);

			const uploaded = await this.upload(bucketName, objectName, data);

			return uploaded;
		} catch (err: unknown) {
			return false;
		}
	}

	async list(bucketName: string): Promise<string[]> {
		try {
			const res = await this.client.get('/objects', {
				params: {
					bucket: bucketName,
				},
			});

			if (res.status != 200) {
				return [];
			}

			return res.data;
		} catch (err: unknown) {
			return [];
		}
	}

	async remove(bucketName: string, objectName: string): Promise<boolean> {
		try {
			const res = await this.client.delete('/objects', {
				params: {
					bucket: bucketName,
					object: objectName,
				},
			});

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
			const res = await this.client.get('/objects', {
				params: {
					bucket: bucketName,
					object: objectName,
				},
			});

			if (res.status !== 200) {
				return null;
			}

			return Buffer.from(res.data);
		} catch (err: unknown) {
			return null;
		}
	}
}
