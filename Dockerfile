FROM eclipse-temurin:8-jre
#FROM openjdk:8-jre-alpine
#FROM openjdk:8-jdk

ENV JAVA_OPTS='-DLOG_LEVEL=INFO'
ADD target/botapp.jar /app.jar

ENTRYPOINT ["java","-jar","-Xmx256m", "-Dspring.profiles.active=prd1", "/app.jar"]
