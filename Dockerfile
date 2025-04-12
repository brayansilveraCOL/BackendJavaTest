# Etapa 1: Compilación del proyecto usando Maven
FROM maven:3.9.6-eclipse-temurin-17 AS builder

# Establece el directorio de trabajo
WORKDIR /app

# Copia los archivos necesarios
COPY pom.xml .
COPY src ./src

# Construye el JAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final más liviana
FROM eclipse-temurin:17-jdk

# Crea un directorio para la app
WORKDIR /app

# Copia el JAR generado
COPY --from=builder /app/target/*.jar app.jar

# Puerto expuesto (ajústalo si usas otro)
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.jar"]
