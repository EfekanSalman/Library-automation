# Library Automation System

A Spring Boot-based library management system designed to handle book lending, reservations, donations, notifications, and reporting. Built with a focus on clean code, SOLID principles, and RESTful API architecture.

## Features
- **User Management**: Register and manage admin and customer accounts.
- **Book Management**: Add, remove, and search books by title or category.
- **Lending**: Borrow and return books with fine calculation for overdue returns.
- **Reservations**: Reserve books that are currently unavailable.
- **Donations**: Record book donations from customers.
- **Notifications**: Send and track notifications for users.
- **Reports**: Generate reports (e.g., most borrowed books, overdue users).

## Tech Stack
- **Backend**: Spring Boot, Spring Data JPA, Hibernate
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **IDE**: Spring Tool Suite (STS)
- **API**: RESTful endpoints with JSON responses
- **Dependencies**: Lombok, Spring Web, Spring Security (planned)

## Prerequisites
- **Java**: JDK 17 or higher
- **Maven**: 3.6.0 or higher
- **PostgreSQL**: 14 or higher
- **STS**: 4.x (optional, for development)

## Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/efekansalman/library-automation.git
cd library-automation
```

### 2. Configure the Database
Install PostgreSQL and create a database named `library`.

Update `src/main/resources/application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/library
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Build the Project
```bash
mvn clean install
```

### 4. Run the Application
**In STS:** Right-click on Library.java > Run As > Spring Boot App.

**In Terminal:**
```bash
mvn spring-boot:run
```

Application will start on `http://localhost:8080`.

## API Endpoints
Test the endpoints using Postman or any API client.

| Method | Endpoint | Description |
|--------|---------|-------------|
| POST | `/api/users/register` | Register a new user |
| GET | `/api/users/{username}` | Find user by username |
| POST | `/api/books` | Add a new book |
| GET | `/api/books/search/title` | Search books by title |
| POST | `/api/lendings/borrow` | Borrow a book |
| PUT | `/api/lendings/return/{id}` | Return a borrowed book |
| POST | `/api/reservations` | Reserve a book |
| POST | `/api/donations` | Record a book donation |
| POST | `/api/notifications` | Send a notification |
| POST | `/api/reports` | Generate a report |

### Example Request
#### Register a User:
```json
POST http://localhost:8080/api/users/register
{
  "username": "testuser",
  "email": "test@example.com",
  "role": "CUSTOMER",
  "penaltyDebt": 0
}
```

## Project Structure
```
Library/
├── src/
│   ├── main/
│   │   ├── java/com/efekansalman/Library/
│   │   │   ├── controller/    # REST API endpoints
│   │   │   ├── dto/           # Data Transfer Objects
│   │   │   ├── entity/        # JPA entities
│   │   │   ├── repository/    # Spring Data JPA repositories
│   │   │   ├── service/       # Service interfaces
│   │   │   ├── service/impl/  # Service implementations
│   │   │   ├── starter/       # Application entry point
│   │   ├── resources/
│   │   │   └── application.properties  # Configuration
├── pom.xml                    # Maven dependencies
├── README.md                  # This file
```


## Contributing
Feel free to fork this repository, submit issues, or send pull requests.

## License
This project is licensed under the MIT License.

