FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD build/libs/*.war app.war
EXPOSE 80
ENTRYPOINT ["java","-jar","app.war"]