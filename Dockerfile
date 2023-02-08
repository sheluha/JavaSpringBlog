FROM openjdk:17-jdk-slim
COPY /target/JavaSpringBlog-0.0.1-SNAPSHOT.jar spring-docker.jar
ENTRYPOINT ["java","-jar","spring-docker.jar"]
EXPOSE 8080