# Job Tracking API

A Spring Boot backend project for tracking job applications. Built with Java, Spring Security, JWT, MySQL, and Swagger UI.

## Features

- User Registration and Login
- JWT-based Authentication
- Role-based Authorization (coming soon)
- CRUD operations for Job Applications
- Swagger API documentation

## Tech Stack

- Java 17
- Spring Boot
- Spring Security + JWT
- MySQL
- Swagger (OpenAPI)
- Maven

## How to Run

1. Clone the repository
2. Configure `application.properties`
3. Run using any IDE or `mvn spring-boot:run`
4. Access Swagger UI at `http://localhost:9091/swagger-ui.html`

## API Endpoints

- `POST /api/auth/register`
- `POST /api/auth/login`
- `GET /api/jobs`
- (More available in Swagger UI)
