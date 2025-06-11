package service

import (
	"GoWorkshop101/api/db"
	"GoWorkshop101/api/model"
	"context"
)

func CreateUser(user model.User) error {
	_, err := db.Pool.Exec(context.Background(), "INSERT INTO users (id, name) VALUES ($1, $2)", user.Id, user.Name)
	if err != nil {
		return err
	}
	return nil
}

func GetUsers() ([]model.User, error) {
	rows, err := db.Pool.Query(context.Background(), "SELECT id, name FROM users")
	if err != nil {
		return nil, err
	}
	var users []model.User
	for rows.Next() {
		var user model.User
		if err := rows.Scan(&user.Id, &user.Name); err != nil {
			return nil, err
		}
		users = append(users, user)
	}
	return users, nil
}
