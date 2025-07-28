package trtl3sdk

import (
	"bytes"
	"encoding/json"
	"fmt"
	"net/http"
)

func (c *Client) CreateBucket(bucketName string) (bool, error) {
	url := fmt.Sprintf("%s/buckets", c.url)

	payload := map[string]string{
		"bucketName": bucketName,
	}
	body, err := json.Marshal(payload)
	if err != nil {
		return false, fmt.Errorf("Error trying to serialize payload: %w", err)
	}

	req, err := http.NewRequest(http.MethodPost, url, bytes.NewBuffer(body))
	if err != nil {
		return false, fmt.Errorf("Error trying to create the request: %w", err)
	}

	req.Header.Set("Content-Type", "application/json")

  req.Header.Set("Authorization", c.token)

	res, err := c.httpClient.Do(req)
	if err != nil {
		return false, fmt.Errorf("Error while doing a request to the server: %w", err)
	}
	defer res.Body.Close()

	if res.StatusCode == http.StatusCreated {
		return true, nil
	}

  return false, fmt.Errorf("Failed trying to create bucket(status: %d)", res.StatusCode)
}
