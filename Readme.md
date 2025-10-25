# 🧩 OrderFlow – Event-Driven Order Processing System
**Tech Stack:** Java 21 · Spring Boot 3 · Spring Data JPA · Kafka · PostgreSQL · Docker · Swagger (OpenAPI)

## 🏗️ Overview
OrderFlow is a microservice-based, event-driven order management system designed to simulate the full order lifecycle:  
`Create Order → Validate Inventory → Process Payment → Notify`.

## ⚙️ Architecture
```
📦 orderflow/
├── 🧱 orderflow-common/ → Shared DTOs, events, enums, Kafka topics
├── 🚀 order-service/ → REST API to create and fetch orders
├── 📦 inventory-service/ → Consumes order-created events and validates stock
├── 💳 payment-service/ → Processes payment (after validation)
├── 🔔 notification-service/ → Listens to all order events for notifications/logs
└── 🐳 docker-compose.yml → Local orchestration (Postgres, Kafka, services)
```

### 🧩 Tech Highlights
- Spring Boot 3 (REST + JPA)
- PostgreSQL
- Swagger / OpenAPI for API docs
- Modular Maven project
- Designed for Kafka-based event streaming (topics: `order-created`, `order-validated`, `order-paid`)

## 🚀 Running Locally
```bash
# Build and run the Order Service
mvn clean package
java -jar order-service/target/order-service-1.0.0.jar
