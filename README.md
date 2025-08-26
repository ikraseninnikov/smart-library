# Smart Library

**Smart Library** is a full-stack library management application built with **Spring Boot**, **Angular**, **Maven**, **Oracle**, **MyBatis**, and **Liquibase**.  
It provides REST APIs for book and user management, a modern web UI, and integration tests using **H2** in-memory database.

## Technology Stack
- **Backend:** Spring Boot, Java 17+, MyBatis, Liquibase, Oracle DB
- **Frontend:** Angular
- **Build Tool:** Maven
- **Database for testing:** H2 (integration tests)
- **API Documentation:** Swagger/OpenAPI
- **Testing:** Integration and unit tests

## Features
- REST API for managing books, authors, and users
- Angular-based frontend for library operations
- Database migrations via Liquibase
- Integration tests with H2
- Book CRUD operations with validation
- API documentation via Swagger

## Getting Started

### Prerequisites
- Java 17+
- Maven
- Node.js & Angular CLI
- Oracle DB (or H2 for testing)
- Docker (optional, for containerization)

### Backend
1. Clone the repository:
- git clone https://github.com/ikraseninnikov/smart-library.git
- cd smart-library

### Build and run backend:
- mvn clean install
- mvn spring-boot:run

### Frontend
Navigate to frontend folder:
- cd frontend
- npm install
- ng serve

### Planned Improvements

- Dockerization: containerize backend and frontend for easier deployment
- UI/UX enhancements: improved user interface, responsive design
- Validation: add more robust backend and frontend validations
- New fields: expand book and user entities with additional attributes
- Security: JWT authentication and role-based access control
- Extended testing: additional integration and end-to-end tests
- API enhancements: filtering, pagination, sorting
- Frontend features: improved table views, search, and inline editing
- API Documentation Swagger