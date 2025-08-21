package goexample

import (
	"fmt"
	"os"

	"github.com/gin-gonic/gin"
	"github.com/nothiaki/trtl3/examples/go-example/api/handler"
	"github.com/nothiaki/trtl3/examples/go-example/internal/storage"
)

func main() {
	r := gin.Default()

	if err := storage.StorageInit(); err != nil {
		fmt.Println("Error while init storage service")
		os.Exit(1)
	}

	r.GET("/cat", handler.FindRandonCatImage)

  r.Run()

}
