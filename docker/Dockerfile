FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} air-travels-api.jar
ENTRYPOINT ["java", "-jar", "air-travels-api.jar"]