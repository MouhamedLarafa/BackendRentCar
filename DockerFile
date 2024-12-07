FROM openjdk:11

ADD http://192.168.33.10:8081/repository/maven-releases/de/tekup/location-app-B/1.0.0/location-app-B-1.0.0.jar /app/rental-car.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/rental-car.jar"]
