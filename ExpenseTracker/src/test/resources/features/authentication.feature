Feature: Authentication (Login/Logout)

  Background:
    Given application base url is configured

  Scenario: Login with valid credentials
    Given I am on the Login page
    When I login with username "test.user" and password "Test@1234"
    Then I should be redirected to the Dashboard page

  Scenario: Login with invalid credentials shows error
    Given I am on the Login page
    When I login with username "invalid" and password "invalid"
    Then I should see an authentication error message

  Scenario: Logout ends the session
    Given I am logged in as username "test.user" with password "Test@1234"
    When I logout
    Then I should be redirected to the Login page
    And I should not be able to access the Expenses List page without logging in
