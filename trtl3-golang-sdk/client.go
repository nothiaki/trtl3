package trtl3sdk

import "net/http"

type Client struct {
	url    string
	token  string
	httpClient *http.Client
}

func Init(url string, token string, httpClient *http.Client) *Client {
	return &Client{
    url: url,
		token: token,
    httpClient: httpClient,
	}
}

