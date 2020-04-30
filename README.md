# User Subscriber

Creates a user and notifies via email.

## Basic flow 

1. An User data is submitted
2. The received data is persisted in a database
3. A notification is sent to user's e-mail

## Environment Variables
Creates an environment file (e.g. _variables.env_) or export the following variables to OS.
```bash
# MONGODB SPRING
SPRING_DATA_MONGODB_HOST=127.0.0.1
SPRING_DATA_MONGODB_PORT=27017
SPRING_DATA_MONGODB_DATABASE=subscriber
SPRING_DATA_MONGODB_USERNAME=(mongodb user)
SPRING_DATA_MONGODB_PASSWORD=(mongodb password)

# Movies API URL
URL_MOVIE_RANDOM=(random movies URL)
```

## Build
```bash
./gradlew clean build
```
## Run
```bash
java -jar user-subscriber-0.0.1.jar
```

## Requests Examples

### Create User
Request
```bash
curl -i -X POST 'http://localhost:8080/users' \
-H 'Content-Type: application/json' \
-d '{
        "name": "John",
        "birthDate": "2000-01-01",
        "email": "john@email.com"
}'
```
Response
```json
{
    "id": "01E6M71VM7Z983MKSPCHHVP1QT",
    "name": "John",
    "birthDate": "2000-01-01",
    "email": "john@email.com",
    "notifyMe": true
}
```

### Get all users
Request
```bash
curl -i -X GET 'http://localhost:8080/users'
```
Response
```json
[
    {
        "id": "01E6M71VM7Z983MKSPCHHVP1QT",
        "name": "John",
        "birthDate": "2000-01-01",
        "email": "john@email.com",
        "notifyMe": true
    },
    {
        "id": "01E6M5Y4APCKH3WFK4Z7CSZ34E",
        "name": "Mary",
        "birthDate": "1989-12-01",
        "email": "mary@email.com",
        "notifyMe": true
    }
]
```

