FROM openjdk:21-oracle
COPY build/libs/*.jar app.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "app.jar"]