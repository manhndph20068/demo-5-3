FROM openjdk:17-jdk-slim
COPY target/demo-hm5-3-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "demo-hm5-3-0.0.1-SNAPSHOT.jar"]