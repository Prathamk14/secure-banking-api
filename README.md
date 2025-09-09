🏦 Secure Banking API

A Spring Boot RESTful API for secure banking operations. This project implements authentication, account management, and transactions with proper security configurations.

📂 Project Structure
secure-banking-api/
 ├── src/
 │   ├── main/
 │   │   ├── java/com/banking/securebankingapi/
 │   │   │   ├── config/         # Security & Password Encoder Config  
 │   │   │   ├── controller/     # REST Controllers (Auth, Account)  
 │   │   │   ├── entity/         # Entities (User, Account, Transaction)  
 │   │   │   ├── repository/     # JPA Repositories  
 │   │   │   ├── service/        # Business Logic Layer  
 │   │   │   └── SecureBankingApiApplication.java  
 │   │   └── resources/
 │   │       ├── application.properties  
 │   │       └── static/templates (if any)  
 │   └── test/java/...           # Unit Tests  
 ├── pom.xml                     # Maven Dependencies  
 └── README.md                   # Documentation

🚀 Features

🔐 User Authentication (Spring Security + Password Encoding)

🧑‍💼 User & Account Management

💳 Transaction Management (Deposit, Withdraw, Transfer)

📜 Secure REST Endpoints

🛡️ Role-based Authorization

🛠️ Tech Stack

Backend: Spring Boot, Spring Security

Database: JPA / Hibernate (configurable to MySQL, H2, PostgreSQL, etc.)

Build Tool: Maven

Language: Java

⚙️ Installation & Setup

Clone the repository

git clone https://github.com/Prathamk14/secure-banking-api
cd secure-banking-api


Build the project using Maven

mvn clean install


Run the application

mvn spring-boot:run


Access API Endpoints

http://localhost:8080/api/

📌 Example Endpoints
Method	Endpoint	Description
POST	/api/auth/register	Register a new user
POST	/api/auth/login	Authenticate & get token
GET	/api/accounts/{id}	Get account by ID
POST	/api/accounts/{id}/deposit	Deposit money
POST	/api/accounts/{id}/withdraw	Withdraw money
GET	/api/transactions	Fetch all transactions
✅ Testing

Run unit tests with:

mvn test
