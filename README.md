# REST-in-peace

Cemetery management REST API using [Spring Boot](http://projects.spring.io/spring-boot/).


## Requirements

For building and running the application you need:

- [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.6](https://maven.apache.org) (Check [how to install Maven](https://howtodoinjava.com/maven/how-to-install-maven-on-windows/))

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `fr.univlorraine.gheintz.RESTinpeace.RestInPeaceApplication` class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

```shell
mvn spring-boot:run
```

## Deploying the application

The easiest way to deploy the sample application is to use Jenkins by visiting : https://jenkins.m2gi.win/job/g6-projet/ and start a build. 

The application will then be publicly available on https://groupe6.m2gi.win/grave

## Testing

Unit tests are written in `REST-in-peace\src\test\java\fr\univlorraine\gheintz\RESTinpeace`.

Run them by executing: `mvn test`

Unit tests are also run automatically before every commit with the `pre-coommit` git hook.

## Git hooks

Git hooks are defined in the `hooks` directory. Copy them in your `.git/hooks` directory.

In the `pre-coommit` hook, update the following lines to match your configuration:

```shell script
export JAVA_HOME="C:\Program Files\Java\jdk1.8.0_231"
export MAVEN_HOME="D:\apache-maven-3.6.3"
```
