# static-gtfs-api

API for working on static-gtfs transit feed data

## Getting Started

### Prerequisites

What things you need to install the software

1. Java 1.8+

### Running locally
```shell script
SPRING_PROFILES_ACTIVE=local gradle clean bootRun
```

TODO

## Running the tests

### Unit Tests
```shell script
./gradlew clean test
```

### Coverage Tests
```shell script
docker run -d --name sonarqube -p 9000:9000 sonarqube
./gradlew clean sonar -Dsonar.host.url=http://localhost:9000 -Dsonar.login=<token>
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Static-GTFS](https://github.com/google/transit/blob/master/gtfs/spec/en/reference.md) - This api implements the transit data specification of Google
* [Spring Boot](https://spring.io/projects/spring-boot) - The  framework used
* [Gradle](https://gradle.org/) - Dependency Management

## Contributing

Not yet available for contributions

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/phakk/static-gtfs-api/tags). 

## Authors

* **Mark John Moreno** - *Initial work* - [phakk](https://github.com/phakk)


## License

This project is licensed under the GNU GENERAL PUBLIC LICENSE - see the [LICENSE](LICENSE) file for details