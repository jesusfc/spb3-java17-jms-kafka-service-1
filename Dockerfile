##
## Usar el plugin de Spotify (fabric8) para construir la imagen
##
FROM eclipse-temurin:17-jdk

# Set author
LABEL Mentainer="jfcaraballo@gmail.com"

# Set the working directory
WORKDIR /application
##COPY /target/${project.build.finalName}.jar /application/${project.build.finalName}.jar
COPY /target/spb3-java17-jms-kafka-service-1-0.0.1-SNAPSHOT.jar /application/spb3-java17-jms-kafka-service-1-0.0.1-SNAPSHOT.jar

# Set Expose port, just for information
EXPOSE 8081

# Run the application
ENTRYPOINT ["java", "-jar", "spb3-java17-jms-kafka-service-1-0.0.1-SNAPSHOT.jar"]
