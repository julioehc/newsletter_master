FROM openjdk:8
ADD target/EmailService.jar EmailService.jar
EXPOSE 8083
ENTRYPOINT ["java","-jar","EmailService.jar"]