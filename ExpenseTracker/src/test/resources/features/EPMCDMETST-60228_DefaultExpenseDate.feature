@regression @expense @date @Jira-EPMCDMETST-60228
Feature: Default expense date to today in Add/Edit form
  As a registered and authenticated user
  I want the expense Date field to auto-default to today when adding a new expense
  So that I can record expenses faster without manual date entry for most cases

  Background:
    Given the application is running

  @smoke @critical
  Scenario: New expense form defaults date to today in application timezone
    When I open the New Expense page
    Then the Date field should default to today in application timezone "UTC"
    And the Date field should be editable

  @functional
  Scenario: User can override the default date on new expense
    When I open the New Expense page
    And I change the Date field to "2026-08-10"
    Then the Date field value should be "2026-08-10"

  @validation @critical
  Scenario: New expense can be submitted with default date
    When I open the New Expense page
    And I fill Amount with "25.50"
    And I fill Description with "Test expense with default date"
    And I select Category "Food"
    And I submit the expense form
    Then the expense should be saved successfully

  @validation
  Scenario: Date field is required and cannot be empty
    When I open the New Expense page
    And I clear the Date field
    And I submit the expense form
    Then I should see a date required validation message

  @edit @critical
  Scenario: Edit expense preserves existing date (does not override to today)
    Given an expense exists with id 1 and date "2026-01-15"
    When I open the Edit Expense page for expense id 1
    Then the Date field value should be "2026-01-15"
    And the Date field should not be today's date

  @boundary
  Scenario Outline: New expense accepts valid date formats
    When I open the New Expense page
    And I change the Date field to "<date>"
    Then the Date field value should be "<date>"

    Examples:
      | date       |
      | 2026-01-01 |
      | 2026-12-31 |
      | 2026-08-16 |
