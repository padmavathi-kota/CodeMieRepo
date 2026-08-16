Feature: Expense date defaulting in application timezone
  As a user creating a new expense
  I want the Date field to default to today in the application configured timezone (UTC)
  So that I can submit faster while retaining correctness.

  Background:
    Given the application is running

  Scenario: Create expense form defaults Date to today in UTC
    When I open the New Expense page
    Then the Date field should default to today in application timezone "UTC"

  Scenario: Create expense form requires Date
    When I open the New Expense page
    And I clear the Date field
    And I submit the expense form
    Then I should see a date required validation message

  Scenario: Edit expense does not overwrite existing date
    Given an expense exists with id 1 and date "2026-01-01"
    When I open the Edit Expense page for expense id 1
    Then the Date field value should be "2026-01-01"
