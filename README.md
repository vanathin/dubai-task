# Microservice: consumer-producer-service
This is a demo microservice for the consumer-producer-thread

### Tech stack used:
1. Java 8
2. Spring Boot 2.4.1
3. Spring REST API (Spring Web)
4. Lombok 1.18.16
5. Spring Data JPA & H2 in-memory database (exposed the client dashboard to see the records)
7. Dockerized (Dockerfile is included)
8. TDD approach using JUnit 5, Mockito, and Spring Boot Test
10. Spring boot starter validation for request validation  

### Steps to build the Docker Image:
##### Step 1: Enter the project directory, 
    cd <project_directory>

##### Step 2: To clean and package the micro-service locally
    mvn clean package
 
##### Step 3: Build the docker image and store it in a local repository (Dockerfile is there in the root of the directory itself)
    docker build -t producer-cosumer-service

##### Step 4: Run the micro-service as a container in the docker:
    docker run -p 8080:8080 producer-cosumer-service

Note: Also [Postman Collection]() has been pushed into the repository for your reference.

---
### In-Memory Database Dashboard:
http://localhost:8080/h2-console/login.jsp   
![H2_DB_Console img missing]()

Note: Username and Password for the in-memory H2 database 
sa/password

---