# QuickBite Restaurant Service

Restaurant and menu management microservice for PS014.

## Port
8082

## Database
PostgreSQL database:
`quickbite_restaurant`

Default development settings are in:
`src/main/resources/application.properties`

Change the PostgreSQL username/password if they are different on your computer.

## Eureka
Eureka Server:
`http://localhost:8761/eureka/`

## Main APIs

### Restaurants
GET `/restaurants`
GET `/restaurants/{id}`
POST `/restaurants`
PUT `/restaurants/{id}`
DELETE `/restaurants/{id}`

### Menu
GET `/restaurants/{restaurantId}/menu`
POST `/restaurants/{restaurantId}/menu`
PUT `/menu/{id}`
DELETE `/menu/{id}`

## Example restaurant JSON

{
  "name": "QuickBite Kitchen",
  "address": "KL University Road",
  "city": "Vijayawada",
  "phone": "9876543210",
  "available": true
}

## Example menu JSON

{
  "name": "Veg Biryani",
  "description": "Fresh vegetable biryani",
  "price": 180.00,
  "available": true
}

## Run

From this folder:

`.\mvnw.cmd spring-boot:run`

Or, if Maven is installed:

`mvn spring-boot:run`
