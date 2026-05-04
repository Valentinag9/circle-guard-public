# --- Etapa 1: Build ---
FROM gradle:8.5-jdk21 AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY services/ services/

RUN ./gradlew :services:circleguard-identity-service:build -x test --no-daemon

# --- Etapa 2: Runtime ---
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

COPY --from=build /app/services/circleguard-identity-service/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
