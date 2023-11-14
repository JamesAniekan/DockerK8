FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY target/DockerK8-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD ["java", "-jar","DockerK8-0.0.1-SNAPSHOT.jar"]