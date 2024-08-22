FROM openjdk:14
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 9100
ENTRYPOINT ["java","-jar","/app.jar"]
