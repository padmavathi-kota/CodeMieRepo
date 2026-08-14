Feature: Edit and Delete Expenses

  Background:
    Given application base url is configured
    And I am logged in as username "test.user" with password "Test@1234"
    And I have created an expense with amount "15.00" currency "USD" date "2026-08-01" category "Food" description "Setup expense"

  Scenario: Edit an existing expense
    When I open the Expenses List page
    And I edit the first expense and set amount to "16.00" and description to "Updated expense"
    Then the first expense row should show amount "16.00" and description "Updated expense"

  Scenario: Edit does not reset date to today
    When I open the Expenses List page
    And I edit the first expense without changing the date
    Then the expense date should remain "2026-08-01"

  Scenario: Delete an existing expense
    When I open the Expenses List page
    And I delete the first expense
    Then the expense should be removed from the list
