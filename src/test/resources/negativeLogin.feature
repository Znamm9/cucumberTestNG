@loginRelated
Feature: login

  Scenario Outline: check negative login
    Given user on login page
    When user enter login <username>
    And enter password <password>
    And user clicks on submit login
    Then login error message should be displayed

    Examples:
      | username | password |
      | vadym    | test123  |
      | v        | 1        |