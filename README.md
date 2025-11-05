# Employee Management Spring Boot Application

# Overview

This project is a simple Spring Boot application for managing employee details such as name, email, and ID. It demonstrates CRUD operations, RESTful design, and testing using JUnit and Mockito.

---

# Features

* Create, update, delete, and fetch employee records
* Fetch employee by **email**
* Fetch employee by **name**
* Global exception handling
* In-memory database (**H2**)
* RESTful API design with meaningful HTTP status codes
* Unit and Integration testing using **JUnit** and **Mockito**

---

# Tech Stack

* **Spring Boot**
* **Spring JPA**
* **H2 Database**
* **Maven**
* **JUnit 5** and **Mockito**

---

# API Endpoints

## Create Employee

**POST** `/api/employees/createEmployee`
**Request Body (JSON):**

```json
{
  "firstname": "shiva",
  "lastname":"sai",
  "address":"India",
  "email": "shiva@gmail.com",
  "phone":"7674972282"
}
```

**Response (200 OK):**

```json
{
  "id": 1,
  "firstname": "shiva",
  "lastname":"sai",
  "address":"India",
  "phone":"7674972282",
  "email": "shiva@gmail.com",
  "message": "Employee created successfully with Id:1"
}
```

---

// Each endpoint that requires a 'methodType' supports three options 'jpa', 'hql', and 'native'. You can select any one of these while calling the API.
#  Fetch Employee by Email

**GET** `http://localhost:8080/api/employees/getEmployeeByEmail/shiva@gmail.com/method/jpa`
**Response:**

```json
{
  "id": 1,
  "firstname": "shiva",
  "lastname":"sai",
  "address":"India",
  "phone":"7674972282",
  "email": "shiva@gmail.com",
  
}
```

---

# Fetch Employee by Name

**GET** `http://localhost:8080/api/employees/getEmployeeByFirstName/shiva/method/hql`

**Response:**

```json
[
  {
    "id": 1,
  "firstname": "shiva",
  "lastname":"sai",
  "address":"India",
  "phone":"7674972282",
  "email": "shiva@gmail.com",
  
  }
]
```

---

#  Update Employee

**PUT** `http://localhost:8080/api/employees/updateEmployee/email/shiva@gmail.com/method/native`
**Request Body (JSON):**

```json
{
  
  "lastname":"vamshi",
  "address":"Usa",
  "phone":"8008961122",
  
}
```

**Response:**

```json
{
  "id": 1,
   "firstname": "shiva",
  "lastname":"vamshi",
  "address":"Usa",
  "phone":"8008961122",
   "email": "shiva@gmail.com",
  "message": "Employee updated successfully"
}
```
# Update Employee Phone Number

**GET** `http://localhost:8080/api/employees/updateEmployeePhone/email/shiva@gmail.com`
**Request Body (JSON):**

```json
{
  
  "phone":"9000100213",
  
}
```

**Response:**

```json
[
  {
  "id": 1,
  "firstname": "shiva",
  "lastname":"vamshi",
  "address":"Usa",
  "phone":"8008961122",
  "email": "shiva@gmail.com",
  
  }
]
```

---

#  Delete Employee

**DELETE** `http://localhost:8080/api/employees/deleteEmployeeByEmail/shiva@gmail.com/method/jpa`
**Response:**

```json
{
  "message": "Employee deleted successfully"
}
```

---
#  Global Exception Handling

Handles errors like:

* Employee not found
* Invalid input
* Database errors
  Returns structured JSON responses with proper HTTP status codes.

---

#  Setup Instructions

1. Install prerequisites:

   * JDK 17 or above
   * Maven 3.8+
   * IDE: STS or IntelliJ

2. Import project into your IDE

3. Set the JDK

4. Configure H2 Database (default settings in `application.properties`)

5. Run the application

6. Access APIs using Postman or any REST client

7. Run Unit Tests

---

# Example H2 Console Access

* URL: `http://localhost:8080/h2-console`
* JDBC URL: `jdbc:h2:mem:testdb`
* Username: `sa`
* Password: 
