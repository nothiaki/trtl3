package trtl3sdk

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"net/http"
)

// POST /buckets
// =======================================

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

	if res.StatusCode != http.StatusCreated {
		return false, fmt.Errorf("Failed trying to create bucket(status: %d)", res.StatusCode)
	}

	return true, nil
}

// =======================================

// GET /buckets
// =======================================

func (c *Client) ListBuckets() ([]string, error) {
	url := fmt.Sprintf("%s/buckets", c.url)

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
		return nil, fmt.Errorf("Failed trying to find created buckets(status: %d)", res.StatusCode)
	}

	bodyBytes, err := io.ReadAll(res.Body)
	if err != nil {
		return nil, fmt.Errorf("Error reading response body: %w", err)
	}

	buckets := []string{}

	err = json.Unmarshal(bodyBytes, &buckets)
	if err != nil {
		return nil, fmt.Errorf("Error when deserialize response %w", err)
	}

	return buckets, nil

}

// =======================================

// DELETE /buckets
// =======================================

func (c *Client) DeleteBucket(bucketName string) (bool, error) {
	url := fmt.Sprintf(
		"%s/buckets?bucket=%s",
		c.url,
		bucketName,
	)

	req, err := http.NewRequest(http.MethodDelete, url, nil)
	if err != nil {
		return false, fmt.Errorf("Error trying to create the request: %w", err)
	}

	req.Header.Set("Authorization", c.token)

	res, err := c.httpClient.Do(req)
	if err != nil {
		return false, fmt.Errorf("Error while doing a request to the server: %w", err)
	}

	defer res.Body.Close()

	if res.StatusCode != http.StatusOK {
		return false, fmt.Errorf("Failed trying to delete bucket (status: %d)", res.StatusCode)
	}

	return true, nil

}

// =======================================
