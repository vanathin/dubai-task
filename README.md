# Microservice: consumer-producer-service
This is a demo microservice for the consumer-producer-thread

### Tech stack used:
1. Java 8
2. Spring Boot 2.4.1
3. Spring REST API (Spring Web)
4. Lombok 1.18.16
5. Spring Data JPA & MySQL
7. Dockerized (Dockerfile is included)
8. TDD approach using JUnit 5, Mockito, and Spring Boot Test
10. Spring boot starter validation for request validation  

### Steps to build the Docker Image:
##### Step 1: Enter the project directory, 
    cd <project_directory>

##### Step 2: To clean and package the micro-service locally
    mvnw clean package

##### Step 3: Use `docker compose` to build and run the both MySQL & micro-service as a docker containers:
    docker-compose up --build
Note: Above docker compose command will run the containers namely,
   1. `ekar-mysql` container for mysql
   2. `ekar-producer-consumer-service` container for microservice

####

Note: Also [Postman Collection]() has been pushed into the repository for your reference.

---
### MySQL Database CLI to verify the transaction information:
##### Prerequisite: 
Docker has to be there on the host machine
##### Step 1: Connect to `ekar-mysql` container using docker cli command, 
    docker exec -it ekar-mysql /bin/bash
##### Step 2: Connect to mysql server
    mysql -usa -ppassword -h localhost -P3306
##### Step 3: display all the available databases,
    show databases;
##### Step 4: switch to ekardb
    use ekardb;
##### Step 5: list all the available tables
    show tables;
##### Step 6: Run the select queries to verify the data
    select * from request_log; select * from counter_log;
Note: Preserve this state until you complete your testing.
##### Step 7: exit from mysql cli
    exit
##### Step 8: exit from container
    exit

   
![H2_DB_Console img missing]()


---