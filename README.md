# Manager
This application provides an initial attempt at providing the manager
functionality for the Prometheus application.  The idea is that we can create 
and manage baskets and their model portfolios.

## Configuring the application
The application is driven from the properties file.
This can be found here:


![Config](./docs/config2.png)
```/src/main/resources/application.properties```


For each environment we need to set the base of the YouTrade REST API.
This enables the deployed application to integrate with the appropriate set of services.

## Running the application
The project is a standard Maven project. To run it from the command line,
type `mvnw` (Windows), or `./mvnw` (Mac & Linux), then open
http://localhost:8080 in your browser.

## Deploying to Production
To create a production build, call `mvnw clean package -Pproduction` (Windows),
or `./mvnw clean package -Pproduction` (Mac & Linux).
This will build a JAR file with all the dependencies and front-end resources,
ready to be deployed. The file can be found in the `target` folder after the build completes.

Once the JAR file is built, you can run it using
`java -jar target/manager-1.0-SNAPSHOT.jar`

## Project structure

- `MainLayout.java` in `src/main/java` contains the navigation setup (i.e., the
  side/top bar and the main menu). This setup uses
- `views` package in `src/main/java` contains the server-side Java views of your application.
- `views` folder in `frontend/` contains the client-side JavaScript views of your application.
- `themes` folder in `frontend/` contains the custom CSS styles.

## Deploying using Docker
To build the Dockerized version of the project, run

```
./mvnw clean package -Pproduction
docker build . -t manager:latest
```

Once the Docker image is correctly built, you can test it locally using

```
docker run -p 8080:8080 manager:latest
```


## Useful Commands 
I maxed out my drive, so needed to clean up docker.  
These are the commands that I used and might be useful to other developers, but not needed for the build

docker rm -f $(docker ps -a -q)
docker rmi $(docker images -a -q)
docker volume rm $(docker volume ls -q)
brew install postgresql@15
brew services start postgresql@15