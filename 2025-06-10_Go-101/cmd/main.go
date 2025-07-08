package main

import (
	"GoWorkshop101/api/db"
	"GoWorkshop101/api/handlers"
	"GoWorkshop101/api/middlewares"
	"github.com/gin-gonic/gin"
)

func main() {
	router := gin.Default()
	db.Init()

	router.Use(middlewares.AuthRequired)

	api := router.Group("/api/users")
	{
		api.GET("/", handlers.GetUsers)
		api.POST("/", handlers.CreateUser)
	}

	router.Run(":8080")
}
