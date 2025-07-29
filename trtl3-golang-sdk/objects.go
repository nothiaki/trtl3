package trtl3sdk

import (
	"bytes"
	"fmt"
	"io"
	"mime/multipart"
	"net/http"
	"path/filepath"
)

func (c *Client) UploadObject(bucket, objectName string, object io.Reader) (bool, error) {
	endpoint := fmt.Sprintf("%s/objects/upload?bucket=%s&object-name=%s", c.url, bucket, objectName)

	var buffer bytes.Buffer
	writer := multipart.NewWriter(&buffer)

	formFile, err := writer.CreateFormFile("object", filepath.Base(objectName))
	if err != nil {
		return false, fmt.Errorf("Error creating form file: %w", err)
	}

	if _, err := io.Copy(formFile, object); err != nil {
		return false, fmt.Errorf("Error copying object content: %w", err)
	}

	if err := writer.Close(); err != nil {
		return false, fmt.Errorf("Error closing writer: %w", err)
	}

	req, err := http.NewRequest(http.MethodPost, endpoint, &buffer)
	if err != nil {
		return false, fmt.Errorf("Error creating request: %w", err)
	}

	req.Header.Set("Content-Type", writer.FormDataContentType())
	req.Header.Set("Authorization", c.token)

	res, err := c.httpClient.Do(req)
	if err != nil {
		return false, fmt.Errorf("Error while doing request: %w", err)
	}
	defer res.Body.Close()

	if res.StatusCode != http.StatusCreated {
		return false, fmt.Errorf("Upload failed with status (%d)", res.StatusCode)
	}

	return true, nil
}

