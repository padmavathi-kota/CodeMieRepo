Feature: Add Expense - Default Date to Today

  Background:
    Given application base url is configured
    And I am logged in as username "test.user" with password "Test@1234"

  Scenario: Add Expense form defaults date to today in yyyy-MM-dd
    When I open the Add Expense page
    Then the Expense Date field should be pre-populated with today's date in "yyyy-MM-dd"

  Scenario: Add an expense using default date
    Given I open the Add Expense page
    When I create an expense with amount "12.50" currency "USD" category "Food" description "Lunch" and keep default date
    Then the expense should be created successfully

  Scenario: Add an expense with a user-selected date
    Given I open the Add Expense page
    When I create an expense with amount "20.00" currency "USD" category "Transport" description "Taxi" and date "2026-08-01"
    Then the expense should be created successfully
