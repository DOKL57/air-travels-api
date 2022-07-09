FROM adoptopenjdk:11-jre-hotspot
EXPOSE 8080
ADD target/air-travels-api-0.0.1-SNAPSHOT.jar air-travels-api.jar
ENTRYPOINT ["java", "-jar", "/air-travels-api.jar"]