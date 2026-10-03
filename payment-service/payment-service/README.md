# QuickBite Payment Service

Port: 8084
Database: quickbite_payment
Eureka: http://localhost:8761/eureka/

POST /payments

{
  "orderId": 1,
  "userId": 1,
  "amount": 350.00
}

GET /payments/{id}
GET /payments/order/{orderId}

The POST endpoint returns:
{
  "success": true,
  "message": "Payment successful"
}
