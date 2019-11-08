# Newsletter Subscription Service
This services makes possible to the user to subscribe to a newsletter. It has a MicroServices architecture compose by four independent microservices.
The main one is the **Newsletter_subscription** service, that has the whole program logic and only can be accessible by public services, the other ones that dependes of it are the email-service, the event-service
and the database, which are not reachable from outside. All of them are based on an Eureka server implementations to allow the communications between them.
The technologies used to develop this project are:

* **Spring Boot**, used to create the applications and the ability to run them as stand-alone. We don't need any external
server to run them.

* **Spring Cloud**, used to make the microservices architecture. Helps a lot in the creation of a server and the registration of the microservices in it.

* **MongoDB**, stores the information of the different created newsletters. As the microservice needs to mantain the information of millions of users mongo allow us to easly manage them

* **Swagger**, creates the API of the newsletter service. It easy to use and his friendly design helps the user to test the microservice.

* **Fabric8 Docker Maven**, allow maven to create the docker images. Very useful when we want to create the maven package and also generate the corresponding docker image.

* **Lombok**, add useful annotations to avoid redundant code. 
## **Preparing the database**
In this step we have to download the mongo image from docker hub.
```
docker pull mongo
```
Also, to be able to execute the database locally we need to install Mongo and use the following command to start it.
```
mongod
```
If you are using windows, you have to navigate to the mongo path
```
(C:\Program Files\MongoDB\Server\{version}\bin)
```
and procced with mongod. If you don't have the folder /data/db/ the program will warn you to created in C:\ ,we can set the db path using --dbpath while launching it.

## Building the project
To build the project we need to perform the following maven instruction in each service. This will generate the jar and also the docker image

```
mvn verify
```
Next, we need to run each generated .jar, if we want to test the application locally.

1. **Eureka Server** 
	```
	java -jar target/EurekaServer.jar
	```
4. **Subscription Service**
	```
	java -jar target/SubscriptionService.jar
	```
2. **Email Service**
	```
	java -jar target/EmailService.jar
	```
3. **Event Service**
	```
	java -jar target/EventService.jar

## Testing our service
To check our Eureka server we have to write in our browser http://localhost:8082                                                
To inspect the Swagger, we have to navigate to it using http://localhost:8081/swagger-ui.html
To test the service we can use postman, curl, or inside the swagger /subscribe method. 
```
curl -X POST "http://localhost:8081/subscribe" -H "accept: application/json" -H "Content-Type: application/json" -d "{ "consentFlag": true, "dateOfBirth": "15-02-2000", "email": "dummyemail@yourdomain.com", "firstName": "Julio", "gender": "Male", "newsLetterCampaign": 12}"
```
## Docker 
If you have already build it with mvn clean go to the next step, if not, use the following command to create the image and the jar
```
mvn verify
```
Once the images are created, navigate to the file named Docker and execute
```
docker-compose up -d
```
This will created five containers including the database. To test them use the same steps in **Testing** paragraph

To stop all the containers use

```
docker-compose down
```
