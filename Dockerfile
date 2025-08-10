FROM openjdk:17-jdk-alpine
COPY target/social-api-server-0.0.1.jar /app/social-api-server-0.0.1.jar
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "social-api-server-0.0.1.jar"]
