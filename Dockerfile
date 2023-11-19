FROM eclipse-temurin:17-jre
COPY target/manager-1.0-SNAPSHOT.jar app.jar
COPY src/main/resources/application.properties application.properties
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar","--spring.config.location","file:./application.properties"]
