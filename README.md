# Library Application

A Spring Boot application for managing a library.

## Installation

1. Clone the repository:
```
git clone https://github.com/EfekanSalman/Library-automation.git

```

2. Navigate to the project directory:
```
cd Library-automation
```

3. Build the project:
```
./gradlew build
```

4. Run the application:
```
./gradlew bootRun
```

The application will start running on `http://localhost:8080`.

## Usage

The Library Application provides an API for managing books, authors, and users. You can perform the following operations:

- Create, read, update, and delete books
- Create, read, update, and delete authors
- Create, read, update, and delete users
- Borrow and return books

## API

The API endpoints are documented using Swagger. You can access the Swagger UI at `http://localhost:8080/swagger-ui.html`.

## Contributing

If you would like to contribute to the Library Application, please follow these steps:

1. Fork the repository
2. Create a new branch for your feature or bug fix
3. Make your changes and commit them
4. Push your changes to your fork
5. Create a pull request

## License

This project is licensed under the [MIT License](LICENSE).

## Testing

The Library Application includes unit tests and integration tests. You can run the tests using the following command:

```
./gradlew test
```

The test results will be available in the `build/reports/tests/test` directory.
