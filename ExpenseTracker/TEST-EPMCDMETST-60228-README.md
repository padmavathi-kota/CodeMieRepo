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

## Traceability Matrix

| Jira ID | Acceptance Criteria | Test Scenario | Status |
|---------|-------------------|---------------|---------|
| EPMCDMETST-60228 | AC1: Add Expense defaults to today | New expense form defaults date | Automated |
| EPMCDMETST-60228 | AC1: Date is editable | User can override default date | Automated |
| EPMCDMETST-60228 | AC2: Submit with default date | Submit with default date | Automated |
| EPMCDMETST-60228 | AC3: Edit preserves date | Edit preserves existing date | Automated |
