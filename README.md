# static-gtfs-api ![Java CI](https://github.com/phakk/static-gtfs-api/workflows/Java%20CI/badge.svg?branch=master&event=push)

API for working on static-gtfs transit feed data

## How it works
1. On startup it loads the gtfs data by parsing the gtfs files from the specified url
2. GTFS data is store only on in-memory database: H2
3. Only read operations are supported

## Getting Started

### Prerequisites

What things you need to install the software

1. Java 1.8+

### Running locally
```shell script
SPRING_PROFILES_ACTIVE=local gradle clean bootRun
```

## Running the tests

### Unit Tests
```shell script
./gradlew clean test
```

### Coverage Tests
```shell script
docker run -d -p 9000:9000 sonarqube
./gradlew clean sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<token>
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Static-GTFS](https://github.com/google/transit/blob/master/gtfs/spec/en/reference.md) - This api implements the transit data specification of Google
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Batch](https://spring.io/projects/spring-batch)
* [Gradle](https://gradle.org/) - Dependency Management

## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE - see the [LICENSE](LICENSE) file for details
