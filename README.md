# In-Memory Social API

A Spring Boot REST API for user registration, authentication (JWT), and post management — all stored in-memory without a database.

## 🚀 Features

- **User Management**
    - User registration
    - User login with JWT token generation
- **Post Management**
    - Create, like, delete posts
    - List all posts or only your posts
- **Authentication**
    - JWT-based security
    - Swagger UI integration with Bearer token support

## 🛠 Tech Stack

- Java 17
- Spring Boot 3.5.4
- Spring Security 6
- JWT Authentication
- springdoc-openapi for Swagger documentation
- ConcurrentHashMap for in-memory storage

## 📂 Project Structure
src/main/java/com/sridhar/socialapi/
- advice/ : Global exception handler
- config/ : Security, Swagger, and other configurations
- controller/ : REST controllers
- dto/ : Request, response, and error data transfer objects
- exception/ : Custom exceptions
- store/ : In memory data handling, act as a DB
- service/ : Business logic
- utils/ : Utility classes for JWT and mapping
- SocialApiServerApplication : Main Spring Boot application class

## 📜 How to Run

1. **Clone the repository**
   ```bash
   git clone https://github.com/sridharbonda/inmemory-social-api
   cd inmemory-social-api
   ```

2. **Build and run**
   ```bash
   mvn spring-boot:run
   ```

API will be available at:
```
http://localhost:8080
```

Swagger UI:
```
http://localhost:8080/swagger-ui.html
```

## 🔑 Using Secured Endpoints in Swagger

1. Open Swagger UI.
2. Click the **Authorize** button (top right).
3. Enter your JWT token in this format:
   ```
   Bearer <your-token>
   ```
4. Authorize and then call secured APIs.

## 📂 API Endpoints

### User Management

**Register a user**
```
POST /auth/signup
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}
```

**Login and get JWT token**
```
POST /auth/login
Content-Type: application/json

{
  "username": "john_doe",
  "password": "password123"
}
```

_Response:_
```
<JWT_TOKEN_STRING>
```

### Post Management (Requires JWT)

**Create a post**
```
POST /social/create
Authorization: Bearer <your-token>
Content-Type: application/json

{
  "content": "Hello World!"
}
```

**Like a post**
```
PUT /social/like/post/{id}
Authorization: Bearer <your-token>
```

**Delete a post**
```
DELETE /social/{id}
Authorization: Bearer <your-token>
```

**List user posts**
```
GET /social/myPosts
Authorization: Bearer <your-token>
```

**List all posts**
```
GET /social/allPosts
Authorization: Bearer <your-token>
```

## ❌ Error Response Format

```
{
  "timestamp": "2025-08-09T10:15:30",
  "status": 409,
  "error": "Conflict",
  "message": "This username is already registered"
}
```

## 📌 Author

**Bonda Sridhar**  
📧 Email: sridhar.bonda123@gmail.com  
🌐 GitHub: [sridharbonda](https://github.com/sridharbonda)
