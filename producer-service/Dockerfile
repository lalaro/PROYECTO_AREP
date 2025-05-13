# producer-service/Dockerfile
# Usa una imagen base de JRE ligera que coincida con tu JDK de compilación
FROM openjdk:17-jdk-slim

# Argumento para el JAR (se pasa durante la construcción en docker-compose)
ARG JAR_FILE=target/*.jar

# Establece el directorio de trabajo
WORKDIR /app

# Copia el JAR construido a la imagen
COPY ${JAR_FILE} app.jar

# Expone el puerto de la aplicación
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]