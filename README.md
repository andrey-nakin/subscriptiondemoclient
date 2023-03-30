# subscriptiondemoclient
Sample GraphQL Subscription Client Application

This is a tiny console application which is a GraphQL subscription client. It assumes to use GraphQL schema of [subscriptiondemo](https://github.com/andrey-nakin/subscriptiondemo) application.

The application is intended to be used for test purposes.

## Requirements

* Java 17 or higher
* Docker

## Build docker image

```shell
./gradlew bootBuildImage
```

## Usage

```shell
docker run -p 8082:8082 docker.io/library/subscriptiondemoclient:0.0.1-SNAPSHOT \
  --app.host=ws://localhost:8082/subscription \
  --app.payload=sample-payload  \
  --app.limit=10
```
