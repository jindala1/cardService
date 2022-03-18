# Crediit Card Rest Service


## Install and run the project
1. download/clone the project

   git clone https://github.com/jindala1/cardService.git


2. Build the project using following maven command from project root folder
* gradlew clean build

3. Create docker image from following command
* docker build -t cardservice .

4. Run Docker Container
* docker run -p 8080:8080 -t cardservice

5. Test the endpoint
* curl -i --user pubsap:pubsap1 http://localhost:8080/v1/api/listCards

6. Stop Docker Container:
* docker stop  docker container ls | grep "cardservice"

7. Docker compose can be used as well : Run the docker-compose using the following command
* docker-compose up -d

8. Stop Docker Container:
* docker-compose down


The app defines following APIs

GET /v1/api/listCards

POST /v1/api/addCard