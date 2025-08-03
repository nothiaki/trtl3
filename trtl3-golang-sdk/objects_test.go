package trtl3sdk

import (
	"bytes"
	"fmt"
	"net/http"
	"os"
	"testing"

	"github.com/jarcoal/httpmock"
)

func TestUploadObject_Success(t *testing.T) {
  httpmock.Activate(t)

  defer httpmock.DeactivateAndReset()

	bucket := "new-bucket"
	objectName := "file.png"

  file := bytes.NewReader([]byte("file content"))

  httpmock.RegisterResponder(
    http.MethodPost,
    fmt.Sprintf(
      "%s/objects/upload?bucket=%s&object=%s",
      url,
      bucket,
      objectName,
      ),
    authHandler(http.StatusCreated, ""),
  )

	c := Init(url, token)

	uploaded, err := c.UploadObject(bucket, file, objectName)
	if err != nil {
		t.Fatalf("Expected no error, but got: %v", err)
	}

	if !uploaded {
		t.Errorf("Expected uploaded to be true, but got false")
	}

}

func TestUploadObjectByPath_Success(t *testing.T) {
  httpmock.Activate(t)

  defer httpmock.DeactivateAndReset()

	bucket := "new-bucket"
	objectName := "obj.png"

	file, err := os.CreateTemp("./", "file.png")
	if err != nil {
		t.Fatalf("Failed to create temp file: %v", err)
	}
	defer os.Remove(file.Name())

  httpmock.RegisterResponder(
    http.MethodPost,
    fmt.Sprintf(
      "%s/objects/upload?bucket=%s&object=%s",
      url,
      bucket,
      objectName,
      ),
    authHandler(http.StatusCreated, ""),
  )


	c := Init(url, token)

	uploaded, err := c.UploadObjectByPath(bucket, file.Name(), objectName)
	if err != nil {
		t.Fatalf("Expected no error, but got: %v", err)
	}

	if !uploaded {
		t.Errorf("Expected uploaded to be true, but got false")
	}

}

