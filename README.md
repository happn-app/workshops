# Workshop

Very simple project for understanding basics of spring.

## Setup

### PostgreSQL
Install and run postgreSQL

```shell
docker pull postgres
docker run  -e POSTGRES_PASSWORD=my_password -d -p 5432:5432 postgres
```

### Build with maven

```shell
mvn clean install
```

##  Project Structure

### application

Contains the spring controllers, the entrypoints of the api.
We use spring-web MVC here https://docs.spring.io/spring-framework/reference/web/webmvc.html

### domain

The business layer.

### infrastructure

The database layer, we use spring-data-jpa and the postgreSQL driver
https://spring.io/projects/spring-data-jpa

## Usage

### Create a user
```shell
curl --location 'http://localhost:8080/users' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "bob",
    "email": "bobleponge@gmail.com"
}'
```

### Get a user by id

```shell
curl --location 'http://localhost:8080/users/700a7825-20a3-487d-b4b8-42ed2f7b65a2'
```
