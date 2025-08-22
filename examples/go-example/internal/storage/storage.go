package storage

import (
	"fmt"
	"net/http"
	"slices"

	trtl3sdk "github.com/nothiaki/trtl3/trtl3-golang-sdk"
)

var trtl3 *trtl3sdk.Client

func StorageInit() error {
	trtl3 = trtl3sdk.Init(
		"http://localhost:7713",
		"trtl3-token",
	)

	bucketName := "cats"

	buckets, err := trtl3.ListBuckets()
	if err != nil {
		fmt.Println("Error while creating bucket")
		return err
	}

	if slices.Contains(buckets, bucketName) {
		fmt.Println("Bucket already exists")
		return nil
	}

	if _, err := trtl3.CreateBucket(bucketName); err != nil {
		fmt.Println("Error while creating bucket")
		return err
	}

	for i := range 3 {
		res, err := http.Get("https://cataas.com/cat")
		if err != nil {
			fmt.Println("Error fetching cat image:", err)
			return err
		}
		defer res.Body.Close()

		_, err = trtl3.UploadObject(
			"cats",
			res.Body,
			fmt.Sprintf("%d.png", i),
		)
		if err != nil {
			fmt.Println("Error uploading image:", err)
			return err
		}
	}

	return nil
}

func FindAllCatImage() ([]string, error) {
	images, err := trtl3.ListObjects("cats")
	if err != nil {
		fmt.Println("Error listing objects:", err)
		return []string{}, err
	}

	return images, nil
}
