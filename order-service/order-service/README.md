# QuickBite Order Service

Order management microservice for PS014.

## Port
8083

## Database
PostgreSQL database:
`quickbite_order`

Change the PostgreSQL username/password in `application.properties` if needed.

## Eureka
`http://localhost:8761/eureka/`

## APIs

GET `/orders`
GET `/orders/{id}`
GET `/orders/user/{userId}`
GET `/orders/restaurant/{restaurantId}`
POST `/orders`
PUT `/orders/{id}/status?status=ACCEPTED`
POST `/orders/{id}/pay`
DELETE `/orders/{id}`

## Example order

{
  "userId": 1,
  "restaurantId": 1,
  "totalAmount": 350.00
}

## Payment communication

`POST /orders/{id}/pay` calls:

`http://localhost:8084/payments`

The exact Payment Service endpoint and request/response fields must match your existing Payment Service. If your completed Payment Service uses a different endpoint, change these two properties:

`payment.service.url`
`payment.service.payment-path`

## Run

`.\mvnw.cmd spring-boot:run`

or:

`mvn spring-boot:run`
