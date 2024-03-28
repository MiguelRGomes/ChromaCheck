FROM openjdk:17
MAINTAINER Miguel Gomes
COPY build/libs/*.jar tcc.jar
EXPOSE 8080
CMD ["java", "-jar", "tcc.jar"]