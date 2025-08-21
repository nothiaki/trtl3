package handler

import (
	"fmt"
	"math/rand"
	"net/http"

	"github.com/gin-gonic/gin"
	"github.com/nothiaki/trtl3/examples/go-example/internal/storage"
)

func FindRandonCatImage(c *gin.Context) {
	imgs, err := storage.FindAllCatImage()
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{
			"message": "Sorry we got an error on our server, try again",
    })
	}

	url := fmt.Sprintf("http://localhost:7713/public/%w", imgs[rand.Intn(2)])

	c.JSON(http.StatusOK, gin.H{
		"goto": url,
	})
}
