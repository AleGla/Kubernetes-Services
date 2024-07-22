FROM maven:3.8.3-jdk-8-slim as builder

WORKDIR /workdir
COPY pom.xml ./
COPY src ./src

RUN ["mvn", "install", "-Dmaven.test.skip=true"]

FROM openjdk:8-jdk-slim
CMD ["catalina.sh","run"]
COPY --from=builder /workdir/target/DetectionService-SNAPSHOT.jar /DetectionService.jar
ENTRYPOINT ["java","-jar","DetectionService.jar"]