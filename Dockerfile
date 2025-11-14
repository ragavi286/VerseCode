FROM openjdk:17

WORKDIR /app

COPY . .

RUN chmod +x mvnw || true

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "target/WebJavaCompiler-1.0-jar-with-dependencies.jar"]
