FROM maven:3.8.3-jdk-8-slim as builder

WORKDIR /workdir
COPY pom.xml ./
COPY src ./src

RUN mvn package -Dskiptests

FROM openjdk:8-jdk-slim
CMD ["catalina.sh","run"]
COPY --from=builder /workdir/target/CacheService-SNAPSHOT.jar /CacheService.jar
ENTRYPOINT ["java","-jar","CacheService.jar"]