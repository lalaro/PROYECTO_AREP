# consumer-service/Dockerfile
# Usa una imagen base de JRE ligera
FROM eclipse-temurin:17-jre-jammy

# Argumento para el JAR
ARG JAR_FILE=target/*.jar

# Directorio de trabajo
WORKDIR /app

# Copia el JAR
COPY ${JAR_FILE} app.jar

# No necesita exponer puerto si solo consume mensajes
# EXPOSE 8081

# Comando de ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]