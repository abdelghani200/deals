# Deals Management System 💰

A Spring Boot service for managing financial deals with robust validation, logging, and persistence.

![Project Status](https://img.shields.io/badge/Status-Production--Ready-green)
![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)

## 🚀 Features

### ✅ Deal Management
- RESTful API for creating and managing financial deals
- Comprehensive data validation
- Unique deal ID enforcement
- Detailed audit logging

### 🛡️ Validation
- Currency code validation
- Amount validation (must be positive)
- Timestamp validation
- Duplicate detection

### 📊 Data Persistence
- PostgreSQL database integration
- JPA/Hibernate ORM
- Efficient data retrieval

## 🛠 Technologies Used

### Core Stack
- Java 21
- Spring Boot 3.5.7
- PostgreSQL 16
- Docker & Docker Compose
- MapStruct
- Lombok

## 🚀 Getting Started

### Prerequisites
- Java 21
- Maven 3.8+
- PostgreSQL 16
- Docker (optional)

### Local Development

1. Clone the repository:
   ```bash
   git clone [https://github.com/abdelghani200/deals.git]
   cd deals

2. Build the project:
   ```bash
   ./mvnw clean install
   ```

3. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

Configure the application properties in `src/main/resources/application.yml`  

Add the following properties:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/dealsdb
    username: postgres
    password: your_password
```

4. Start the application:
   ```bash
   ./mvnw spring-boot:run
   ```

🐳 Docker Setup

1. Build the Docker image:
   ```bash
   docker build -t deals-service .
   ```

2. Start the containers:
   ```bash
   docker-compose up -d
   ```

3. Access the application:
   - Open your browser and navigate to `http://localhost:8081`

4. Stop the containers:
   ```bash
   docker-compose down
   ```

📚 API Documentation
POST /api/deals
Create a new financial deal.

Request Body:
```json
{
  "id": "string (unique deal identifier)",
  "orderingCurrencyIsoCode": "string (ISO currency code, e.g., USD)",
  "toCurrencyIsoCode": "string (ISO currency code, e.g., EUR)",
  "amount": 0.0,
  "dealTimestamp": "2025-11-15T00:00:00Z"
}
```

Response Body:
```json
{
  "id": "string (unique deal identifier)",
  "orderingCurrencyIsoCode": "string (ISO currency code, e.g., USD)",
  "toCurrencyIsoCode": "string (ISO currency code, e.g., EUR)",
  "amount": 0.0,
  "dealTimestamp": "2025-11-15T00:00:00Z"
}
```

Response Status:
```json
201 Created
``` 


🏗️ Project Structure

```
deals/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── example/
│   │   │           └── deals/
│   │   │               ├── controller/
│   │   │               ├── dto/
│   │   │               ├── entity/
│   │   │               ├── exception/
│   │   │               ├── mapper/
│   │   │               ├── repository/
│   │   │               ├── service/         
│   │   └── resources/
│   │       └── application.yml
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── deals/
│                    └── service/impl/
│                       └── DealsApplicationTests.java
├── Dockerfile
├── docker-compose.yml
├── Makefile
├── pom.xml
└── README.md
```

🧪 Running Tests

1. Run the tests:
   ```bash
   ./mvnw test
   ```

2. Run the tests with coverage:
   ```bash
   ./mvnw test --coverage
   ```