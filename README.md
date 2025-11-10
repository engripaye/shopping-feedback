# 🛍️ Shopping Feedback Backend

**Spring Boot + MySQL REST API for Customer Feedback Collection**

This project is a fully functional backend application built with **Java 21** and **Spring Boot 3.5.x**, designed to collect, store, and manage customer feedback for a shopping platform. It includes RESTful API endpoints, form validation, database persistence via JPA, and an optional HTML form for quick feedback submission.

---

## 🚀 Features

✅ **REST API for feedback submission & retrieval**
✅ **MySQL persistence via Spring Data JPA**
✅ **Request validation using Jakarta Validation**
✅ **Docker Compose setup for MySQL**
✅ **Optional Thymeleaf form for direct user input**
✅ **Maven-based build for easy setup and deployment**

---

## 🧩 Tech Stack

| Component            | Technology                  |
| -------------------- | --------------------------- |
| **Language**         | Java 21                     |
| **Framework**        | Spring Boot 3.5.x           |
| **Database**         | MySQL 8                     |
| **ORM**              | Spring Data JPA (Hibernate) |
| **Validation**       | Jakarta Validation          |
| **Build Tool**       | Maven                       |
| **Containerization** | Docker & Docker Compose     |
| **Optional UI**      | Thymeleaf Template Engine   |

---

## 🏗️ Project Structure

```
shopping-feedback/
│
├── src/
│   ├── main/java/com/example/shoppingfeedback/
│   │   ├── controller/       # REST API endpoints
│   │   ├── dto/              # Request DTOs & validation
│   │   ├── model/            # JPA entity definitions
│   │   ├── repository/       # Data access layer
│   │   ├── service/          # Business logic layer
│   │   └── ShoppingFeedbackApplication.java
│   └── resources/
│       ├── application.properties
│       └── templates/        # Optional Thymeleaf HTML form
│
├── pom.xml
└── docker-compose.yml
```

---

## ⚙️ Configuration

Edit the database credentials in
`src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/shopping_feedback?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=changeme
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## 🐳 Docker Setup (Recommended)

Run MySQL locally using Docker Compose:

```bash
docker compose up -d
```

MySQL will start with:

* **Database:** `shopping_feedback`
* **User:** `appuser`
* **Password:** `apppassword`

Then update your `application.properties`:

```properties
spring.datasource.username=appuser
spring.datasource.password=apppassword
```

---

## 🧠 API Overview

**Base URL:** `http://localhost:8080/api/feedback`

### ▶️ Submit Feedback

```bash
curl -X POST http://localhost:8080/api/feedback \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "contact": "+2348012345678",
    "rating": 4,
    "itemsNotFound": "Olive oil, Wheat flour",
    "priceToReduce": "Tomatoes are expensive",
    "improvementSuggestion": "Open more checkout counters"
  }'
```

### 📋 Get All Feedback

```bash
curl http://localhost:8080/api/feedback
```

### 🔍 Get Feedback by ID

```bash
curl http://localhost:8080/api/feedback/1
```

---

## 🧪 Run Locally

1️⃣ Start MySQL (either locally or via Docker).
2️⃣ Update your credentials in `application.properties`.
3️⃣ Build and run the app:

```bash
mvn clean package
java -jar target/shopping-feedback-0.0.1-SNAPSHOT.jar
```

Or run directly from your IDE:

```
ShoppingFeedbackApplication.java
```

Access the API at:

```
http://localhost:8080/api/feedback
```

---

## 🖥️ Optional: Web Feedback Form

If you enabled Thymeleaf, open:
`src/main/resources/templates/feedback-form.html`

You can serve this from a simple controller to collect feedback via a browser form.

---

## 🧰 Future Enhancements

* Add **authentication** for feedback admins
* Integrate **Swagger/OpenAPI** documentation
* Add **email notifications** for new feedback
* Introduce **pagination and filtering**
* Deploy with **Docker + Spring Boot container**

---

## 🧾 License

This project is licensed under the **MIT License** — feel free to use and modify for your learning or production needs.

---

## 💡 Inspiration

Designed as a learning-friendly yet production-ready Spring Boot starter demonstrating:

> *“How to build a REST API with MySQL integration and validation — from code to container.”*

---
