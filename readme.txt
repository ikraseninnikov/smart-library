# Smart Library

Smart Library is an educational web application for managing books, users, and loans. 
It demonstrates a full-stack approach with a Spring Boot backend, Angular frontend, and integration with a relational database. 
This project is intended for learning purposes and to explore modern Java enterprise development.

## Features

- View, add, and delete books, users, and loans
- Interactive Angular frontend
- REST API with Spring Boot
- Database integration with Oracle (H2 used for integration tests)
- Basic CRUD operations implemented
- DTO mapping for frontend display

## Technology Stack

1. **Spring Boot** – backend framework  
2. **Java** – programming language  
3. **MyBatis** – persistence framework for SQL mapping  
4. **Oracle** – main relational database  
5. **Liquibase** – database versioning and migrations  
6. **Angular** – frontend framework  
7. **REST API** – communication between frontend and backend  
8. **H2** – in-memory database for integration tests  

## TODO / Future Improvements

9. Integrate **Kafka** for asynchronous messaging  
10. Split into **microservices** for books, users, and loans  
11. Add **Swagger/OpenAPI** for API documentation  
12. Implement **JWT security** for authorization  

## Running the Project

### Backend

1. Make sure **Java 17+** and **Maven** are installed.  
2. Configure the Oracle database connection in `application.properties`.  

Start the Spring Boot backend:

mvn spring-boot:run

Frontend
Navigate to the frontend folder:

npm install
Start the Angular development server:

ng serve --proxy-config proxy.conf.json

Open your browser at http://localhost:4200/smartlibrary

Notes
This project is for educational purposes and demonstrates a full-stack Java + Angular workflow.

The current version provides a basic skeleton, ready for feature extensions and microservice architecture.