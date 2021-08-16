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

