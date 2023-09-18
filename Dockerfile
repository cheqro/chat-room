FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY jars/chat-1.jar chat-1.jar
ENTRYPOINT ["java","-jar","/chat-1.jar"]
EXPOSE 8080