# QuickBite API Gateway

Port: 8080

Run:
1. Start Discovery Service on 8761.
2. Start Auth, User, Restaurant, Payment and Order services.
3. Run ApiGatewayApplication.java as Spring Boot App.

Routes:
 /auth/**        -> AUTH-SERVICE
 /users/**       -> USER-SERVICE
 /restaurants/** -> RESTAURANT-SERVICE
 /menu/**        -> RESTAURANT-SERVICE
 /orders/**      -> ORDER-SERVICE
 /payments/**    -> PAYMENT-SERVICE
