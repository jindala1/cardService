FROM openjdk:11
EXPOSE 8080
ADD /build/libs/cardService-0.0.1-SNAPSHOT.jar cardservice.jar
ENTRYPOINT ["java" , "-jar" , "cardservice.jar"]