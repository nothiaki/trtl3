package trtl3sdk

import "testing"

func TestInit(t *testing.T) {
	c := Init(url, token)

	if c == nil {
		t.Errorf("Init returned a nil client")
	}

	if c.url != url {
		t.Errorf("Init url got %s, wanted %s", c.url, url)
	}

	if c.token != token {
		t.Errorf("Init token got %s, wanted %s", c.token, token)
	}

}
