Feature: Expense Charts and Reports

  Background:
    Given application base url is configured
    And I am logged in as username "test.user" with password "Test@1234"

  Scenario: Chart page loads
    When I open the Chart page
    Then the chart/report page should be displayed

  Scenario: GetChart endpoint page loads
    When I open the GetChart page
    Then the chart/report page should be displayed
