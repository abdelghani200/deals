# Deals Import Service

A Spring Boot service for importing transaction data (deals) from CSV files to a data warehouse.

## Description

This service allows you to:
- Import transaction data from CSV files
- Validate imported data
- Store data in a PostgreSQL database 
- Provide a REST API for managing imports

## Prerequisites

- Java 21
- Maven 3.8+
- PostgreSQL 13+
- Docker (optional for deployment)

## Installation

1. Clone the repository:
   ```bash
   git clone [REPO_URL]
   cd deals
   ```

2. Set up PostgreSQL database:
   - Create a PostgreSQL database
   - Configure credentials in `application.properties`

3. Install dependencies:
   ```bash
   ./mvnw clean install
   ```

## Configuration

Copy the `application.properties.example` file to `application.properties` and modify the settings according to your environment:

```properties
# Database configuration
spring.datasource.url=jdbc:postgresql://localhost:5433/deals_db
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```

## Getting Started

To start the application:

```bash
./mvnw spring-boot:run
```

The application will be available at: [http://localhost:8080](http://localhost:8080)

## API Endpoints

### Deals Management
- `POST /api/deals/import` - Import deals from a CSV file
  - Request: `multipart/form-data` with CSV file
  - Response: `201 Created` with import summary or `400 Bad Request` for invalid data
  - Required permissions: `DEAL_WRITE`

- `GET /api/deals` - Get all deals with pagination
  - Query parameters:
    - `page`: Page number (default: 0)
    - `size`: Items per page (default: 20)
    - `sort`: Sort by field (e.g., `date,desc`)
  - Response: `200 OK` with paginated list of deals
  - Required permissions: `DEAL_READ`

- `GET /api/deals/{id}` - Get deal by ID
  - Path parameter: `id` - Deal ID
  - Response: `200 OK` with deal details or `404 Not Found`
  - Required permissions: `DEAL_READ`

- `PUT /api/deals/{id}` - Update a deal
  - Path parameter: `id` - Deal ID
  - Request: JSON object with deal details
  - Response: `200 OK` with updated deal or `404 Not Found`
  - Required permissions: `DEAL_WRITE`

- `DELETE /api/deals/{id}` - Delete a deal
  - Path parameter: `id` - Deal ID
  - Response: `204 No Content` on success or `404 Not Found`
  - Required permissions: `DEAL_DELETE`

### Statistics Endpoints
- `GET /api/deals/stats/summary` - Get deals summary statistics
  - Response: `200 OK` with total deals count, total amount, etc.
  - Required permissions: `DEAL_READ`

- `GET /api/deals/stats/by-date` - Get deals grouped by date
  - Query parameters:
    - `startDate`: Start date (ISO format)
    - `endDate`: End date (ISO format)
  - Response: `200 OK` with date-based aggregation
  - Required permissions: `DEAL_READ`

## Project Structure

```
src/
├── main/
│   ├── java/com/example/deals/
│   │   ├── controller/    # REST Controllers
│   │   ├── dto/           # Data Transfer Objects
│   │   ├── entity/        # JPA Entities
│   │   ├── exception/     # Exception handling
│   │   ├── mapper/        # MapStruct Mappers
│   │   ├── repository/    # JPA Repositories
│   │   └── service/       # Business logic
│   └── resources/         # Configuration files
└── test/                  # Unit and integration tests
```

## Technologies Used

- **Backend**: Spring Boot 3.5.7
- **Database**: PostgreSQL
- **Object Mapping**: MapStruct
- **Build Tool**: Maven
- **Java**: 21
- **Lombok**: For reducing boilerplate code

## Docker Deployment

1. Build the Docker image:
   ```bash
   docker build -t deals-service .
   ```

2. Start the containers:
   ```bash
   docker-compose up -d
   ```
