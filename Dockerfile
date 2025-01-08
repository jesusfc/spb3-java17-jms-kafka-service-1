##
## Usar el plugin de Spotify (fabric8) para construir la imagen
##
FROM eclipse-temurin:17-jdk

# Set author
LABEL Mentainer="jfcaraballo@gmail.com"

# Set Expose port, just for information
EXPOSE 8081

# Set the working directory
WORKDIR /application
COPY target/${project.build.finalName}.jar /application/${project.build.finalName}.jar

# Run the application
ENTRYPOINT ["java", "-jar", "${project.build.finalName}.jar"]
