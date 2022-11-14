# Bob's Marketplace - Backend


## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Git command line tool](https://help.github.com/articles/set-up-git)
- Your preferred IDE
  * [VS Code](https://code.visualstudio.com) (preferred)
  * [Eclipse](https://www.eclipse.org/m2e/) (with the m2e plugin)
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## Cloning the application

On the command line, run:
```shell
git clone https://github.com/jaydxn1/Bobs-Marketplace.git
```
Any further references to the command line will be from within the cloned application's base directory.

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.bobsmarketplace.prototype.Application` class from your IDE. On certain IDEs, there are automated build and run functions that can simplify the process.

You can also use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) (preferred) on your command line like so:

```shell
mvn spring-boot:run
```

### Environment
By default, the application will run on port 8080. Feel free to change the port, along with any other environmental properties, within the `src/main/resources/application.properties` file.

You can access the necessary APIs through [http://localhost:8080](http://localhost:8080) by appending the specific endpoints to the URL. More on the endpoints below.

>_Please ensure that this application is up and running first before launching the [frontend](https://github.com/Pyrolf/bobs_marketplace_frontend)._

## Database
The application is currently connected to an instance of Amazon's Relational Database Service for MySQL, already populated with preexisting data for ease of viewing on the frontend. 

Should you wish to switch to using your own local/cloud database services, the MySQL database to be used can be configured under the `src/main/resources/application.properties` file.

## API information
The information on the different endpoints and functions can be viewed in [more detail here](https://documenter.getpostman.com/view/17665160/UVyswaj1).
