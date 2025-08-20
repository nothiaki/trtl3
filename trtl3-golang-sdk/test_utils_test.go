package trtl3sdk

import (
	"net/http"

	"github.com/jarcoal/httpmock"
)

var (
	url   = "http://localhost:8080/"
	token = "trtl3"
)

func authHandler(status int, body string) httpmock.Responder {
	return func(req *http.Request) (*http.Response, error) {
		if req.Header.Get("Authorization") != token {
			return httpmock.NewStringResponse(http.StatusUnauthorized, ""), nil
		}
		return httpmock.NewStringResponse(status, body), nil
	}
}
