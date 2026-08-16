# EPMCDMETST-60228: Test Automation Documentation

## Story Summary
**Title:** Expense user: Default expense date to today in Add/Edit form

**Description:** As a user of the expense tracker, I want the Date field to default to today's date when adding a new expense, so that I can quickly record expenses without manually entering the date each time.

## Test Scope and Coverage

### Acceptance Criteria Mapping

#### AC1: Add Expense - Default Date to Today
**Status:** Automated

**Test Scenarios:**
- New expense form defaults date to today in application timezone
- User can override the default date on new expense

#### AC2: Add Expense - Submit with Default Date
**Status:** Automated

**Test Scenarios:**
- New expense can be submitted with default date
- Date field is required and cannot be empty

#### AC3: Edit Expense - Preserve Existing Date
**Status:** Automated

**Test Scenarios:**
- Edit expense preserves existing date (does not override to today)

## Test Framework Architecture

### Technology Stack
- **Language:** Java 11
- **Framework:** Cucumber BDD (v7.18.1)
- **UI Automation:** Selenium WebDriver (v4.23.0)
- **Driver Management:** WebDriverManager (v5.9.2)
- **Build Tool:** Maven
- **Test Runner:** JUnit Platform

### Test Execution

#### Using Maven
```bash
mvn clean test -Dtest=EPMCDMETST60228TestRunner
```

### Test Reports

1. HTML Report: target/cucumber-reports/EPMCDMETST-60228.html
2. JSON Report: target/cucumber-reports/EPMCDMETST-60228.json
3. JUnit XML: target/cucumber-reports/EPMCDMETST-60228.xml

## Traceability Matrix — EPMCDMETST-60228

| Jira AC | Requirement | Gherkin Scenario / Outline | Tags | Key Automation Implementation |
|---|---|---|---|---|
| **AC1** | Add Expense form defaults **Date** to **today** in application timezone | `Scenario: New expense form defaults date to today in application timezone "UTC"` | `@smoke @critical @Jira-EPMCDMETST-60228` | Feature: `src/test/resources/features/EPMCDMETST-60228_DefaultExpenseDate.feature` • Steps: `ExpenseDateEnhancedSteps.java` |
| **AC1** | User can override default date on new expense | `Scenario: User can override the default date on new expense` | `@regression @functional @Jira-EPMCDMETST-60228` | Steps validate date editability/override behavior via UI interactions |
| **AC2** | New expense can be submitted using default date | `Scenario: New expense can be submitted with default date` | `@regression @critical @Jira-EPMCDMETST-60228` | Steps cover submit flow and verify saved record has expected date |
| **AC2** | Date is required and cannot be empty | `Scenario: Date field is required and cannot be empty` | `@regression @validation @Jira-EPMCDMETST-60228` | Steps clear the date field and assert validation message / submit blocked behavior |
| **AC3** | Edit expense preserves existing date (does not override to today) | `Scenario: Edit expense preserves existing date (does not override to today)` | `@regression @edit @critical @Jira-EPMCDMETST-60228` | Steps create/select an expense with a non-today date, open edit, and verify date remains unchanged |
| (Edge) | Boundary/edge date values supported and persist correctly | `Scenario Outline: New expense accepts valid date formats` with Examples (`2026-01-01`, `2026-12-31`, `2026-08-16`) | `@regression @boundary @Jira-EPMCDMETST-60228` | Data-driven UI input + assertion of saved/displayed date |

### Runner / Execution Entry Point
- Runner: `src/test/java/com/expensetracker/ui/runners/EPMCDMETST60228TestRunner.java`
- Command:
  ```bash
  mvn clean test -Dtest=EPMCDMETST60228TestRunner
  ```

### Report Outputs
- `target/cucumber-reports/EPMCDMETST-60228.html`
- `target/cucumber-reports/EPMCDMETST-60228.json`

### Execution Prerequisites
See: `ExpenseTracker/docs/test-execution/EPMCDMETST-60228_Execution.md`  
(Key dependencies: app running on port `1001`, MySQL on `localhost:3306`, and `baseUrl=http://localhost:1001` in `config.properties`.)

## Detailed Execution Guide
For comprehensive setup instructions including Docker MySQL configuration, troubleshooting, and step-by-step execution procedures, refer to:

**[EPMCDMETST-60228 Execution Guide](docs/test-execution/EPMCDMETST-60228_Execution.md)**
