@regression @Jira-EPMCDMETST-60027
Feature: Add Todo inline validation and accessible error messaging
  As a registered user
  I want the Add Todo form to validate required fields and show accessible errors
  So that I can fix issues immediately and submit successfully

  Background:
    Given I am logged in to ExpenseTracker
    And I navigate to the Todo dashboard

  @smoke
  Scenario: Required field validation prevents submission and shows inline errors
    When I submit the Add Todo form with an empty task name and no due date
    Then the Add Todo form should not be submitted
    And I should see the task name required error message
    And I should see the due date required error message
    And focus should move to the first invalid field

  Scenario: Missing task name only prevents submission and focuses task name
    When I submit the Add Todo form with an empty task name and a valid due date
    Then the Add Todo form should not be submitted
    And I should see the task name required error message
    And I should not see the due date required error message
    And focus should move to the task name field

  Scenario: Missing due date only prevents submission and focuses due date
    When I submit the Add Todo form with a valid task name and no due date
    Then the Add Todo form should not be submitted
    And I should not see the task name required error message
    And I should see the due date required error message
    And focus should move to the due date field

  Scenario: Errors clear when user fixes inputs and submission succeeds
    Given required field errors are displayed on the Add Todo form
    When I enter a valid task name
    And I select a valid due date
    Then the task name error message should disappear
    And the due date error message should disappear
    When I submit the Add Todo form with valid inputs
    Then the todo should be added successfully

  Scenario: Invalid inputs are marked with aria-invalid and associated error text via aria-describedby
    When I submit the Add Todo form with an empty task name and no due date
    Then the task name field should have aria-invalid set to true
    And the due date field should have aria-invalid set to true
    And the task name field should be associated to its error message via aria-describedby
    And the due date field should be associated to its error message via aria-describedby

  Scenario: Error live region announces the form has errors
    When I submit the Add Todo form with an empty task name and no due date
    Then the error summary live region should announce form errors
