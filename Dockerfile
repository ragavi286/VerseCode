FROM eclipse-temurin:17

WORKDIR /app

COPY . .

RUN mvn -q -e -DskipTests package

CMD ["java", "-jar", "target/WebJavaCompiler-1.0-jar-with-dependencies.jar"]

