# Getting Started

Follow this quick guide to get the project running on your computer.

## Requirements

For retrieving, building and running the application you'll need:

- [Git](https://git-scm.com/downloads)
- [JDK 8](https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3.6](https://maven.apache.org) (Check [how to install Maven](https://howtodoinjava.com/maven/how-to-install-maven-on-windows/))

An integrated development environment (IDE) is strongly recommended.
Consider using [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [Eclipse](https://www.eclipse.org/).

## 1. Cloning the repository

```shell
git clone https://github.com/gilleshz/REST-in-peace
```

## 2. Running the application

You can run the application with you IDE or directly in command prompt.

#### 2.1. Running in IntelliJ IDEA

To run the project with IntelliJ IDEA, simply execute the `main` method in the `fr.univlorraine.gheintz.RESTinpeace.RestInPeaceApplication` class.

#### 2.2 Running without an IDE

Run the following command in the project directory to launch the project without an IDE.

```shell
mvn spring-boot:run
```

## 3. Querying the API

When the app is running, the API's uri will be http://localhost:8080/.

You can see the available routes with swagger at http://localhost:8080/swagger-ui.html

## 4. Contributing

If you wish to contribute to this API, see [Contributing](Contributing.md).
