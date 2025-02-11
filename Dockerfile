##
## Usar el plugin de Spotify (fabric8) para construir la imagen
##
FROM eclipse-temurin:17-jdk

# Set author
LABEL Mentainer="jfcaraballo@gmail.com"

# Set the working directory
WORKDIR /application
COPY target/*.jar /application/spb3-java17-jms-kafka-service-1.jar

# Run the application
ENTRYPOINT ["java", "-jar", "spb3-java17-jms-kafka-service-1.jar"]