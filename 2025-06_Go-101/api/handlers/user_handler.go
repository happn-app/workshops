package handlers

import (
	"GoWorkshop101/api/model"
	"GoWorkshop101/api/service"
	"github.com/gin-gonic/gin"
	"github.com/google/uuid"
	"net/http"
)

func GetUsers(c *gin.Context) {
	users, err := service.GetUsers()
	if err != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"message": "Could not retrieve users", "error": err.Error()})
		return
	}
	c.JSON(http.StatusOK, users)
}

func CreateUser(c *gin.Context) {
	var user model.User
	err := c.ShouldBindJSON(&user)
	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{"Could not deserialize user": err.Error()})
		return
	}
	user.Id = uuid.New().String()
	err2 := service.CreateUser(user)
	if err2 != nil {
		c.JSON(http.StatusInternalServerError, gin.H{"message": "Could not create user", "error": err.Error()})
		return
	}
	c.JSON(http.StatusCreated, user)
}
