Feature: Filter expenses by Month and Year

  Background:
    Given application base url is configured
    And I am logged in as username "test.user" with password "Test@1234"

  Scenario: Filter expense list by month and year
    Given I open the Expenses List page
    When I filter expenses by month "8" and year "2026"
    Then the expense list should refresh based on the filter
