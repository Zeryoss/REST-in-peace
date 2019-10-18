# REST-in-peace

Cemetery management REST API using [Spring Boot](http://projects.spring.io/spring-boot/).


## Requirements

For building and running the application you need:

- [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 4](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `fr.univlorraine.gheintz.RESTinpeace.RestInPeaceApplication` class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

```shell
mvn spring-boot:run
```

## Deploying the application

The easiest way to deploy the sample application is to use Jenkins by visiting : https://jenkins.m2gi.win/job/g6-projet/ and start a build. 

The application will then be publicly available on https://groupe6.m2gi.win/grave
