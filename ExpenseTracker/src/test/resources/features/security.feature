Feature: Security - Access control

  Background:
    Given application base url is configured

  Scenario: Unauthenticated user cannot access expenses list
    Given I am not logged in
    When I open the Expenses List page
    Then I should be redirected to the Login page
