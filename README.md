# ecommerce-mini

A RESTful E-Commerce backend API built with Java and Spring Boot, demonstrating layered architecture with Product, Customer, and Order management.

## Tech Stack

- Java 21
- Spring Boot 3.5
- Spring Data JPA
- Hibernate
- H2 In-Memory Database

## Project Structure

```
ecommerce-mini/
└── src/
    └── main/
        ├── java/
        │   └── ecommerce_mini/
        │       ├── product/
        │       │   ├── Product.java
        │       │   ├── ProductRepository.java
        │       │   ├── ProductService.java
        │       │   └── ProductController.java
        │       ├── customer/
        │       │   ├── Customer.java
        │       │   ├── CustomerRepository.java
        │       │   ├── CustomerService.java
        │       │   └── CustomerController.java
        │       ├── order/
        │       │   ├── Order.java
        │       │   ├── OrderRepository.java
        │       │   ├── OrderService.java
        │       │   └── OrderController.java
        │       └── EcommerceMiniApplication.java
        └── resources/
            └── application.properties
```

## Features

- Create and manage products with name, price, and stock
- Register customers with name and email
- Place orders with automatic stock validation and deduction
- Atomic order placement using @Transactional - if anything fails, the entire operation rolls back
- Auto-generated database schema using Hibernate DDL

## API Endpoints

### Product

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/products | Create a new product |

### Customer

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/customers | Register a new customer |

### Order

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | /api/orders?customerId={id}&productId={id}&quantity={n} | Place an order |

## How to Run

1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Run `EcommerceMiniApplication.java`
4. Server starts at `http://localhost:8080`
5. H2 Console available at `http://localhost:8080/h2-console`

## Sample Requests

**Create a Product**
```json
POST /api/products
{
  "name": "iPhone 15",
  "price": 79999.0,
  "stock": 10
}
```

**Register a Customer**
```json
POST /api/customers
{
  "name": "Arpan",
  "email": "arpan@gmail.com"
}
```

**Place an Order**
POST /api/orders?customerId=1&productId=1&quantity=2

## Key Concept

Order placement is wrapped in a single `@Transactional` method. This means stock deduction and order creation either both succeed or both fail together, ensuring data consistency.