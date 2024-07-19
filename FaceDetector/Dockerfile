FROM tomcat:8.0.51-jre8-alpine
CMD ["catalina.sh","run"]
COPY target/TestUbuntu-SNAPSHOT.jar TestUbuntu.jar
ENTRYPOINT ["java","-Dlog4j.configurationFile=/log4j2/testubuntu_log4j2.xml","-jar","TestUbuntu.jar","--spring.config.location=file:/config/testUbuntu.properties"]