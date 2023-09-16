# Stage 2: Create the final image
FROM adoptopenjdk/openjdk17:alpine-jre
WORKDIR /app
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar demo.jar
EXPOSE 8080
CMD ["java", "-jar", "demo.jar"]