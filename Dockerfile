# Stage 1: Build the application
FROM maven:3.8.2-openjdk17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn package