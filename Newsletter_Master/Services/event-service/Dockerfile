FROM openjdk:8
ADD target/EventService.jar EventService.jar
EXPOSE 8085
ENTRYPOINT ["java","-jar","EventService.jar"]