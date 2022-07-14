FROM openjdk:17-alpine
EXPOSE 8080
VOLUME /tmp
ARG JAR_FILE
COPY ${JAR_FILE} petrolalert.jar
ENTRYPOINT ["java", "-jar", "/petrolalert.jar"]
