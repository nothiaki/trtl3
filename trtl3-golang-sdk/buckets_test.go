package trtl3sdk

import (
	"fmt"
	"net/http"
	"testing"

	"github.com/jarcoal/httpmock"
)

func TestCreateBucket_Success(t *testing.T) {
	httpmock.Activate(t)

	defer httpmock.DeactivateAndReset()

	bucketName := "new-bucket"

	httpmock.RegisterResponder(
		http.MethodPost, fmt.Sprintf("%s/buckets", url),
		authHandler(http.StatusCreated, ""),
	)

	c := Init(url, token)

	created, err := c.CreateBucket(bucketName)
	if err != nil {
		t.Fatalf("Expected no error, but got: %v", err)
	}

	if !created {
		t.Errorf("Expected success to be true, but got false")
	}

}

func TestListBuckets_Success(t *testing.T) {
	httpmock.Activate(t)

	defer httpmock.DeactivateAndReset()

	bucketName := "new-bucket"

	httpmock.RegisterResponder(
		http.MethodGet, fmt.Sprintf("%s/buckets", url),
		authHandler(http.StatusOK, fmt.Sprintf(`["%s"]`, bucketName)),
	)

	c := Init(url, token)

	buckets, err := c.ListBuckets()
	if err != nil {
		t.Fatalf("Expected no error, but got: %v", err)
	}

	if buckets[0] != bucketName {
		t.Errorf("Expected bucket list to contains %s, but does not have", bucketName)
	}

}
