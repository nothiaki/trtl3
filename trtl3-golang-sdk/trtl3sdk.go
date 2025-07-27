package trtl3sdk

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

