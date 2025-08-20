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

func TestListObjects_Success(t *testing.T) {
	httpmock.Activate(t)

	defer httpmock.DeactivateAndReset()

	bucket := "new-bucket"
	object := "file.png"

	httpmock.RegisterResponder(
		http.MethodGet, fmt.Sprintf("%s/objects?bucket=%s", url, bucket),
		authHandler(http.StatusOK, fmt.Sprintf(`["%s"]`, object)),
	)

	c := Init(url, token)

	objectList, err := c.ListObjects(bucket)
	if err != nil {
		t.Fatalf("Expected no error, but got: %v", err)
	}

	if objectList[0] != object {
		t.Errorf("Expected object list to contains %s, but does not have", object)
	}

}
