A beginner-friendly Quiz Application built using Java Spring Boot. This project is designed to help understand the fundamentals of building a RESTful web service using Spring Boot architecture with layered structure — Controller, Service, DTO, Model, and Repository.

✨ Features
Create, update, delete, and fetch quiz questions
Take a quiz and get results
Clean architecture with separation of concerns
Uses Spring Data JPA for database interaction
Simple and easy to extend

🏗️ Project Architecture
src/
├── controller   # Handles HTTP requests and maps them to service methods
├── service      # Contains business logic
├── dto          # Data Transfer Objects to move data between layers
├── model        # Entity classes mapped to database tables
├── repository   # Spring Data JPA repositories for DB operations
├── QuizAppApplication.java  # Main class

💡 Tech Stack
1.Java 17
2.Spring Boot
3.Spring Web
4.Spring Data JPA
5.PostgreSQL
6.Maven

🚀 Getting Started
Prerequisites
Java JDK installed
Maven installed
IDE (e.g., IntelliJ IDEA, Eclipse, VS Code)

Clone the repository
git clone https://github.com/SyamKumar1234/quiz-app-springboot.git
cd quiz-app
Configure database
the project uses  PostgreSQL:

properties
spring.application.name=quizapp
spring.datasource.url=jdbc:postgresql://localhost:5432/springbootdb
spring.datasource.username=postgres
spring.datasource.password=12345
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update

Build and run
mvn clean install
mvn spring-boot:run
The app will start at: http://localhost:8080
