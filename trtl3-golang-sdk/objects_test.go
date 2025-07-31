package trtl3sdk

import (
	"bytes"
	"fmt"
	"net/http"
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
      "%s/objects/upload?bucket=%s&object-name=%s",
      url,
      bucket,
      objectName,
      ),
    authHandler(http.StatusCreated, ""),
  )

	c := Init(url, token)

	uploaded, err := c.UploadObject(bucket, objectName, file)
	if err != nil {
		t.Fatalf("Expected no error, but got: %v", err)
	}

	if !uploaded {
		t.Errorf("Expected uploaded to be true, but got false")
	}

}

