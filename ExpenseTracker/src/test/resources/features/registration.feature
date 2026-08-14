Feature: User Registration

  Background:
    Given application base url is configured

  Scenario: Register a new user with valid details
    Given I am on the Registration page
    When I register with first name "Test" last name "User" email "unique+1@local.test" password "Test@1234"
    Then registration should be submitted successfully

  Scenario Outline: Registration validation errors
    Given I am on the Registration page
    When I register with first name "<firstName>" last name "<lastName>" email "<email>" password "<password>"
    Then I should see a registration validation message containing "<message>"

    Examples:
      | firstName | lastName | email            | password   | message |
      |          | User     | a@b.com          | Test@1234  | First   |
      | Test     |          | a@b.com          | Test@1234  | Last    |
      | Test     | User     | invalid-email    | Test@1234  | email   |
      | Test     | User     | a@b.com          | short      | password|
