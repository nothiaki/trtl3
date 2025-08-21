package goexample

import (
  "net/http"

	trtl3sdk "github.com/nothiaki/trtl3/trtl3-golang-sdk"
  "github.com/gin-gonic/gin"
)

func main() {

	trtl3 := trtl3sdk.Init(
		"http://localhost:7713/",
		"trtl3-token",
	)

	r := gin.Default()

	r.GET("/cat", func(c *gin.Context) {
    c.JSON(http.StatusOK, gin.H{
      "message": "cat",
    })
  })

  r.Run()

}
