FROM eclipse-temurin:21.0.3_9-jre

ADD "./target/recipe-service.jar" /recipe-service.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Xms64m", "Xmx1024m", "-jar", "recipe-service.jar"]