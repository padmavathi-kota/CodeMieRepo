# EPMCDMETST-60228 — Execution Guide (UI Automation)

## Purpose
This document describes the prerequisites and steps to execute the end-to-end UI automation for Jira story **EPMCDMETST-60228**:

**Story:** Expense user: Default expense date to today in Add/Edit form  
**Scope:** Selenium + Cucumber + JUnit UI tests (runner: `EPMCDMETST60228TestRunner`)

---

## Prerequisites

### 1) Java / Maven
- Java 11+ installed
- Maven installed (or use Maven Wrapper if present)

Verify:
```bash
java -version
mvn -version
```

### 2) Google Chrome (Headless)
- Chrome installed locally
- Tests are designed to run **Chrome headless** using WebDriverManager

### 3) ExpenseTracker application must be running
The UI tests assume the Spring Boot app is already started and reachable.

**App config excerpt (`application.properties`):**
- Port: `server.port=1001`
- Timezone: `app.timezone=UTC`

### 4) MySQL must be running (required)
The application requires MySQL reachable at:

- Host: `localhost`
- Port: `3306`
- DB: `ExpenseTracker`
- User: `root`
- Password: `12345678`
- JDBC URL:
  `jdbc:mysql://localhost:3306/ExpenseTracker?ServerTimezone=UTC`

If MySQL is not running, the application fails to start with:
`CJCommunicationsException: Communications link failure` / `Connection refused`

> Note: Hibernate is configured with `spring.jpa.hibernate.ddl-auto=update`, so schema/tables are expected to be auto-created on startup.

---

## Important Configuration Alignment (Base URL/Port)
The application runs on **port 1001**, but the UI test config currently shows:

`src/test/resources/config.properties`
```properties
baseUrl=http://localhost:8080
browser=chrome
headless=false
explicitWaitSeconds=10
```

### Required update before execution
Update `config.properties` to:
```properties
baseUrl=http://localhost:1001
browser=chrome
headless=true
explicitWaitSeconds=10
```

This ensures:
- Tests target the correct application port (`1001`)
- Chrome runs headless (CI-friendly / no UI required)

---

## Option A (Recommended): Start MySQL using Docker

### 1) Start MySQL 8 container
```bash
docker run --name expensetracker-mysql ^
  -e MYSQL_ROOT_PASSWORD=12345678 ^
  -e MYSQL_DATABASE=ExpenseTracker ^
  -p 3306:3306 ^
  -d mysql:8
```

(For Mac/Linux, remove `^` and use `\` line continuations instead.)

### 2) Confirm MySQL is healthy
```bash
docker ps
docker logs expensetracker-mysql
```

Optional: connect and verify DB exists:
```bash
docker exec -it expensetracker-mysql mysql -uroot -p12345678 -e "SHOW DATABASES;"
```

### 3) Stop / remove container (when done)
```bash
docker stop expensetracker-mysql
docker rm expensetracker-mysql
```

---

## Start the ExpenseTracker application

From repository root:
```bash
cd ExpenseTracker
mvn clean spring-boot:run
```

Verify the app is up:
- Open: `http://localhost:1001`
- Or check logs indicate successful startup

---

## Execute the UI test suite for the story

In a **separate terminal** (while the app is running):

```bash
cd ExpenseTracker
mvn clean test -Dtest=EPMCDMETST60228TestRunner
```

---

## Test Reports (Artifacts)

The runner is configured to generate reports under:

- HTML report:
  - `ExpenseTracker/target/cucumber-reports/EPMCDMETST-60228.html`
- JSON report:
  - `ExpenseTracker/target/cucumber-reports/EPMCDMETST-60228.json`

After execution, open the HTML report in a browser:
- `target/cucumber-reports/EPMCDMETST-60228.html`

---

## Troubleshooting

### 1) Application fails with MySQL communications link failure
**Symptom:**
`CJCommunicationsException: Communications link failure` / `Connection refused`

**Cause:**
MySQL is not running or not reachable on `localhost:3306`.

**Fix:**
- Start MySQL service locally **or** use Docker steps in Option A.

### 2) UI tests point to wrong port (8080 vs 1001)
**Symptom:**
- Tests fail to load pages / time out / connection refused.

**Fix:**
Update `config.properties`:
```properties
baseUrl=http://localhost:1001
```

### 3) Chrome UI pops up / test unstable on desktop
**Fix:**
Set:
```properties
headless=true
```

---

## Notes / Future Improvement (Optional)
To remove the manual MySQL dependency, consider a follow-up enhancement:
- Use **Testcontainers MySQL** for local/CI
- Or add a docker-compose for app + db startup
