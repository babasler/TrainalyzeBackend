# --- STAGE 1: Build mit Maven ---
FROM maven:3.9-eclipse-temurin-17 AS build

# Arbeitsverzeichnis
WORKDIR /app

# Nur die POM und Abhängigkeiten zuerst (für Caching)
COPY pom.xml ./
COPY src ./src

# Build ausführen
RUN mvn clean install 

# --- STAGE 2: Run mit JRE ---
FROM eclipse-temurin:17-jre

# Arbeitsverzeichnis
WORKDIR /app

# .jar aus vorherigem Build kopieren
COPY --from=build /app/target/*.jar app.jar

# Port (optional)
EXPOSE 8084

# App starten
ENTRYPOINT ["java", "-jar", "app.jar"]
