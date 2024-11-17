FROM maven:3.9.9-openjdk-17 AS build
COPY . .
Run mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/BornLaeraLeikur-0.0.1-SNAPSHOT.jar BornLaeraLeikur.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar","BornLaeraLeikur"]
