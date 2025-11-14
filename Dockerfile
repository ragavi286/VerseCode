FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn -q -e -DskipTests package


# ---- Runtime image ----
FROM eclipse-temurin:17

WORKDIR /app

COPY --from=build /app/target/WebJavaCompiler-1.0-jar-with-dependencies.jar app.jar

CMD ["java", "-jar", "app.jar"]


