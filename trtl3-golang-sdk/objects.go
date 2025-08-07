package trtl3sdk

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"mime/multipart"
	"net/http"
	"os"
	"path/filepath"
)

// POST /objects/upload
// =======================================

func (c *Client) UploadObject(
	bucket string,
	object io.Reader,
	objectName string,
) (bool, error) {
	endpoint := fmt.Sprintf(
		"%s/objects/upload?bucket=%s&object=%s",
		c.url,
		bucket,
		objectName,
	)

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

func (c *Client) UploadObjectByPath(
	bucket string,
	path string,
	objectName string,
) (bool, error) {
	file, err := os.Open(path)
	if err != nil {
		return false, fmt.Errorf("Error while opening file: %w", err)
	}
	defer file.Close()

	return c.UploadObject(bucket, file, objectName)
}

func (c *Client) ListObjects(bucketName string) ([]string, error) {
	url := fmt.Sprintf("%s/objects?bucket=%s", c.url, bucketName)

	req, err := http.NewRequest(http.MethodGet, url, nil)
	if err != nil {
		return nil, fmt.Errorf("Error trying to create the request: %w", err)
	}

  req.Header.Set("Authorization", c.token)

	res, err := c.httpClient.Do(req)
	if err != nil {
		return nil, fmt.Errorf("Error while doing a request to the server: %w", err)
	}

	defer res.Body.Close()

	if res.StatusCode != http.StatusOK {
    return nil, fmt.Errorf("Failed trying to find created objects(status: %d)", res.StatusCode)
	}

 	bodyBytes, err := io.ReadAll(res.Body)
	if err != nil {
		return nil, fmt.Errorf("Error reading response body: %w", err)
	}

  objects := []string{}

  err = json.Unmarshal(bodyBytes, &objects)
  if err != nil {
    return nil, fmt.Errorf("Error when deserialize response %w", err)
  }

  return objects, nil

}

// =======================================
