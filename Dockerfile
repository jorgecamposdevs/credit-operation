# syntax=docker/dockerfile:1

FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /workspace/app

COPY . .

RUN chmod +x ./gradlew

RUN --mount=type=cache,target=/root/.gradle \
    ./gradlew clean bootJar


FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

RUN addgroup --system spring && \
    adduser --system --ingroup spring spring

USER spring:spring

# copia o JAR gerado pelo Spring Boot
COPY --from=builder /workspace/app/build/libs/*.jar app.jar

EXPOSE 8080

# forma correta de executar Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]