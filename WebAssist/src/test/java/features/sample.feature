Feature: Login to WebAssist
Scenario: Login to WebAssist with invalid user name
    Given Enter username password
    When wrong id provided
    Then verify page not opening