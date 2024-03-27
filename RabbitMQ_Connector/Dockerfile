FROM maven:3.8.3-openjdk-17 as builder

WORKDIR /workdir
COPY pom.xml ./
COPY src ./src

RUN mvn package -Dskiptests

FROM openjdk:17.0-jdk-slim
CMD ["catalina.sh","run"]
COPY --from=builder /workdir/target/RabbitMQ_Connector-SNAPSHOT.jar /RabbitMQ_Connector.jar
ENTRYPOINT ["java","-jar","RabbitMQ_Connector.jar"]