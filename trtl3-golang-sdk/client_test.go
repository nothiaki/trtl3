package trtl3sdk

import "testing"

func TestInit(t *testing.T) {

  url := "httpp://localhost:8080/"
	token := "trtl3"

	client := Init(url, token)

	if client == nil {
		t.Errorf("Init returned a nil client")
	}

	if client.url != url {
		t.Errorf("Init url got %s, wanted %s", client.url, url)
	}

	if client.token != token {
		t.Errorf("Init token got %s, wanted %s", client.token, token)
	}

}

