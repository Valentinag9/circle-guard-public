FROM gradle:8.7-jdk21-alpine AS builder
WORKDIR /app
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle/ gradle/
COPY services/circleguard-dashboard-service/ services/circleguard-dashboard-service/
RUN sed -i 's/\r$//' gradlew && \
    chmod +x gradlew && \
    ./gradlew :services:circleguard-dashboard-service:bootJar --no-daemon
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/services/circleguard-dashboard-service/build/libs/*.jar app.jar
EXPOSE 8084
ENTRYPOINT ["java", "-jar", "app.jar"]
