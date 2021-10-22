FROM openjdk:11 as build
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw install -DskipTests

FROM openjdk:11
VOLUME /tmp
ARG DEPENDENCY=/workspace/app/target/
COPY --from=build ${DEPENDENCY}/kafka-*.jar /app/kafka-consumer.jar
RUN ls -R /app/
ENTRYPOINT ["java","-jar","/app/kafka-consumer.jar"]