package middlewares

import (
	"github.com/gin-gonic/gin"
	"net/http"
)

func AuthRequired(c *gin.Context) {
	authHeader := c.GetHeader("Api-key")
	if authHeader != "very secret key" {
		c.JSON(http.StatusUnauthorized, gin.H{"message": "Authorization header is required"})
		c.Abort()
		return
	}

	// If the token is valid, proceed to the next handler
	c.Next()
}
