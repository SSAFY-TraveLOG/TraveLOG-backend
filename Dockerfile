FROM openjdk:8-jdk-alpine
ENV APP_HOME=/root/Travelog-backend/
WORKDIR $APP_HOME
COPY target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]