# Java-Super-Market
Super Market system, with products, inventory, cashiers, admin and sales reports

APPLICATION.YML:
server:
  error:
    include-message: always
    include-binding-errors: always

spring:
  datasource:
    password: 
    url: jdbc:postgresql://localhost:5432/
    username: 
  jpa:
    hibernate:
      ddl-auto: create-drop
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
        format_sql: true
    show-sql: true
    

CREATING DATABASES:
- CREATE DATABASE employee;
- GRANT ALL PRIVILEGES ON DATABASE "employee" TO postgres;
- CREATE DATABASE history;
- GRANT ALL PRIVILEGES ON DATABASE "history" TO postgres;
- CREATE DATABASE inventory;
- GRANT ALL PRIVILEGES ON DATABASE "inventory" TO postgres;
- CREATE DATABASE product;
- GRANT ALL PRIVILEGES ON DATABASE "product" TO postgres;

POSTMAN COMMANDS:
// Get a list of all entities
- GET http://localhost:8080/api/v1/employees
- GET http://localhost:8080/api/v1/inventories
- GET http://localhost:8080/api/v1/products
- GET http://localhost:8080/api/v1/histories

// add /{id} to get a specific one

- POST http://localhost:8080/login //returns a JWT that allows you to bypass security for all other commands
{
    "login":"admin",
    "password":"admin"
}
Remember, the JWT must be inserted in the HEADER with the key "Authorization" and the value "Bearer insert_key_here", no quotation marks.
It also has an expiration time of 30 minutes.

- POST http://localhost:8080/api/v1/employees //creates an employee
{    
    "login":"admin",
    "password":"admin",
    "salary": 5000.00,
    "role": "ADMIN"
}

To create a history you must first create a product, then create an inventory.
Since you can't subtract inexisting products from an inexisting inventory

- POST http://localhost:8080/api/v1/products //creates a product
{    
    "name":"Banana",
    "price": 5.00,
}

- POST http://localhost:8080/api/v1/inventories //creates an inventory without a product
{    
    "quantity": 100,
}
- PUT http://localhost:8080/api/v1/inventories/1/1 //sets the inventory of ID 1 to contain product of id 1.
OR
- POST http://localhost:8080/api/v1/inventories //creates an inventory with an EXISTING product
{
    "product":{
        "id": 0,        
        "name":"Banana",
        "price": 5.00,        
    }
    "quantity": 100,
}

- POST http://localhost:8080/api/v1/histories //creates history, considered a purchase of the specified product
{    
    "productId": 0,
    "quantity": 10,
}

- GET http://localhost:8080/api/v1/histories/reports/mostsales/5 //gets the 5 products with the most sales
