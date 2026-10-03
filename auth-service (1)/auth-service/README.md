# QuickBite Auth Service

JWT authentication service for PS014.

Port: 8081
Database: quickbite_auth
Eureka: http://localhost:8761/eureka/

## Register

POST /auth/register

{
  "name": "Test User",
  "email": "testuser@example.com",
  "password": "password123"
}

## Login

POST /auth/login

{
  "email": "testuser@example.com",
  "password": "password123"
}

The login response contains a JWT token.

If your existing project uses different PostgreSQL credentials or a different JWT secret, update application.properties.

Run:
.\mvnw.cmd spring-boot:run
