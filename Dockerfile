# Use a base image with Java 17 (or the version specified in your POM)
FROM openjdk:17-jre-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/*.jar app.jar

# Expose the port that your Spring Boot application listens on (if needed)
EXPOSE 8080

# Define the command to run your Spring Boot application
CMD ["java", "-jar", "app.jar"]
