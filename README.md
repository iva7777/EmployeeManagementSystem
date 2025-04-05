# 👩🏻‍💼 Employee Management System

A simple and clean **Employee Management System** built with **Java** and **Spring Boot** that supports basic **CRUD operations** along with some interesting business logic queries. The application uses a layered architecture and includes an in-memory **H2 database** for easy testing and development.

## 📌 Features

- **CRUD Operations** for:
  - Employees
  - Teams
  - Positions
- **Custom Queries**:
  - `getExperiencedFemaleEmployees`: Fetch all female employees with more than a specified number of years of experience.
  - `getAllDirectManagers`: Retrieve all employees who are managing at least one team.

## 🧱 Architecture

The project follows a clean, modular layered architecture:

```
EmployeeManagementSystem/
│
├── api/                # REST Controllers for Employees, Teams, Positions
│
├── bll/                # Business Logic Layer
│   ├── dto/            # DTOs for all entities
│   ├── mapper/         # MapStruct mappers
│   └── service/        # Service interfaces and implementations
│
├── common/             # Common utility classes, response wrappers, etc.
│
├── dal/                # Data Access Layer
│   ├── entity/         # JPA Entities
│   └── repository/     # Spring Data JPA Repositories
│
├── db/                 # H2 Database schema and batch files
│   ├── schema.sql
│   └── data.sql
│
├── resources/          # Static resources and application configuration
│   └── application.properties
│
└── EmployeeManagementSystemApplication.java
```

## 🛠️ Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Data JPA**
- **H2 Database**
- **MapStruct** for mapping DTOs and Entities
- **Swagger / Springdoc OpenAPI**
- **Maven** for build management

## 🧪 How to Run

1. **Clone the repo**:
   ```bash
   git clone https://github.com/iva7777/EmployeeManagementSystem.git
   cd EmployeeManagementSystem
   ```

2. **Run the application**:
   ```bash
   ./mvnw spring-boot:run
   ```

3. **Access the H2 Console**:
   ```
   URL: http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:mem:testdb
   User Name: sa
   Password: (leave blank)
   ```

4. **Explore the API via Swagger UI**:
   ```
   URL: http://localhost:8080/swagger-ui.html
   ```

## 📘 Swagger Sample

Here's a snippet of what the Swagger/OpenAPI config and endpoint might look like:

### Example Swagger Config with Springdoc (in `pom.xml`):

```xml
<dependency>
  <groupId>org.springdoc</groupId>
  <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
  <version>2.1.0</version>
</dependency>
```

### Sample Controller Method:

```java
@Operation(summary = "Get all experienced female employees", description = "Returns a list of female employees with more than X years of experience")
@GetMapping("/experienced-females")
public ResponseEntity<List<EmployeeDto>> getExperiencedFemaleEmployees(@RequestParam int minYears) {
    List<EmployeeDto> result = employeeService.getExperiencedFemaleEmployees(minYears);
    return ResponseEntity.ok(result);
}
```

When running, this endpoint will be documented in Swagger UI for easy testing and documentation.

## 🗂️ Database Schema

The system is built upon the following relational structure:

![ER Diagram](https://github.com/iva7777/EmployeeManagementSystem/blob/main/er-diagram.jpg?raw=true)

- **Employees** belong to one **Team** and hold one **Position**
- Each **Team** has a **Manager** (who is also an Employee)
- Each **Position** has a title and a hierarchy level

## 📐 Architecture Overview
![Architecture Diagram](https://github.com/iva7777/EmployeeManagementSystem/blob/main/architecture-diagram.png?raw=true)

![User Interface Diagram](https://github.com/iva7777/EmployeeManagementSystem/blob/main/Diagram%20UI.jpg?raw=true)

Here is also visible a possible UI design realization made with Figma:
![UI Organization Chart Screen](https://github.com/iva7777/EmployeeManagementSystem/blob/main/Screen.jpg?raw=true)

## 🤝 Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.

**Made with ❤️ using Java & Spring Boot**
```

---

Let me know if you'd like a `swagger-config` class added too (for grouping APIs or customizing titles/descriptions), or if you want a working example of the Swagger-generated output.
