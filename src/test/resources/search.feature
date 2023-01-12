@smoke
Feature: search on wikipedia

  Scenario Outline: check search mechanism works fine
    Given I navigate to home page
    When type <word> in search field
    And press enter
    Then search results should contain <word>
    And total area should be greater than 600000

    Examples:
      | word    |
      | Ukraine |