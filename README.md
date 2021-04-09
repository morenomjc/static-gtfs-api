# static-gtfs-api 

![Java CI](https://github.com/phakk/static-gtfs-api/workflows/Java%20CI/badge.svg?branch=master&event=push) ![Snyk Scan](https://github.com/phakk/static-gtfs-api/workflows/Snyk%20Container/badge.svg?branch=master&event=push)

API for working on static-gtfs transit feed data

## How it works
1. On startup it loads the gtfs data by parsing the gtfs files from the specified url. Defaults to github. See `application-github.yaml`.
2. GTFS data is store only on in-memory database: `h2`
3. Only read operations are supported

## Getting Started

### Prerequisites

What things you need to install the software

1. Java 1.8+
2. Gradle 5.6.2

### Running locally

#### From Source
1. Modify the `gtfs.static.source` property to a local directory or URL.
2. Run via gradle bootRun. Example below points to github repo.
```shell script
SPRING_PROFILES_ACTIVE=github gradle clean bootRun
```

#### From Jar
1. Build the jar file
```shell script
gradle clean build
```
2. Run the jar file
```shell script
java -jar -Dspring.profiles.include=github build/libs/static-gtfs-0.0.1-SNAPSHOT.jar
```

#### From Docker
1. Build the jar file
```shell script
gradle clean build
```
2. Build the docker image
```shell script
docker build -t local/static-gtfs-api .
```
3. Run the docker image
```shell script
docker run -p 8080:8080 local/static-gtfs-api
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
