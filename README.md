# 🔐 Spring Boot Role-Based Access Control App

A comprehensive Spring Boot application implementing **Role-Based Access Control (RBAC)** using **Spring Security**. 
Users can **register**, securely **login**, and access views based on their assigned roles (`ADMIN`, `USER`).

---

## 🚀 Features

- ✅ User registration with secure password encryption (`BCrypt`)
- 🔐 Spring Security for login/authentication
- 🧑‍⚖️ Role-based authorization (`ADMIN`, `USER`)
- 📄 Custom login, home, and access-denied pages
- 🧠 Thymeleaf templates
- 💾 MySQL database integration
- 📊 Sample data for testing

---

## 💻 Tech Stack

- Java 17+
- Spring Boot 3.x
- Spring Security
- Spring Data JPA (Hibernate)
- MySQL
- Thymeleaf
- Maven

---

## 📁 Project Structure

```
src/
├── config/         # Spring Security configuration
├── controller/     # Login, Register, Role-based views
├── entity/         # Employee entity
├── repository/     # JPA repositories
├── service/        # User registration, loading by username
└── resources/
    ├── templates/  # login.html, home.html, user.html, admin.html
    └── application.properties
```

---

## 🛠️ Setup & Run

### 1. Clone Repository

```bash
git clone https://github.com/your-username/spring-security-auth-role.git
cd spring-security-auth-role
```

### 2. Configure MySQL

Make sure you have a database named `Employee` and set these in `src/main/resources/application.properties`:

```properties
server.port=8081
spring.datasource.url=jdbc:mysql://localhost:3306/Employee?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=Root@1234
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Run the App

```bash
mvn spring-boot:run
```

Open: [http://localhost:8081/login](http://localhost:8081/login)

---

## ✨ Endpoints

| Method | Endpoint      | Description               | Access     |
|--------|---------------|---------------------------|------------|
| POST   | `/register`   | Register new user         | Public     |
| GET    | `/login`      | Login form                | Public     |
| GET    | `/home`       | Common homepage           | Authenticated |
| GET    | `/admin`      | Admin dashboard           | ADMIN only |
| GET    | `/user`       | User dashboard            | USER only  |
| GET    | `/access-denied` | Unauthorized access     | Public     |

---

## 🧪 Sample Users

You can test login using existing records:

| Username      | Password   | Role   |
|---------------|------------|--------|
| Haroon        | (BCrypt)   | ADMIN  |
| Ahmad         | (BCrypt)   | USER   |
| Ali Hamza     | (BCrypt)   | USER   |

> Passwords are securely stored and checked using `BCryptPasswordEncoder`.

---

## 🧾 Sample `Employee` Table Data

| ID  | Name           | Salary    | Joining Date | Role  |
|-----|----------------|-----------|---------------|--------|
| 5   | Haroon         | 85,000    | 2025-05-14    | ADMIN |
| 7   | Haroon Rasheed | 200M      | 2025-06-14    | ADMIN |
| 8   | Ahmad          | 450,000   | 2025-08-14    | USER  |
| 9   | Hamza          | 450,000   | 2025-08-14    | USER  |
| 10  | Ali Hamza      | 450,000   | 2025-08-14    | USER  |

---

## 🛡️ Security Highlights

```java
http
  .authorizeRequests()
    .antMatchers("/admin").hasRole("ADMIN")
    .antMatchers("/user").hasRole("USER")
    .antMatchers("/register", "/login").permitAll()
    .anyRequest().authenticated()
  .and()
  .formLogin()
    .loginPage("/login")
    .defaultSuccessUrl("/home", true)
  .and()
  .logout()
    .logoutSuccessUrl("/login?logout")
  .and()
  .exceptionHandling()
    .accessDeniedPage("/access-denied");
```

---




