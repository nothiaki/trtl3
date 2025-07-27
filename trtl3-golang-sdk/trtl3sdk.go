package trtl3sdk

import "fmt"

type Client struct {
	token string
}

func Init(token string) *Client {
	return &Client{
		token: token,
	}
}

func (c *Client) Ping() string {
	return "pong (token: " + c.token + ")"
}

