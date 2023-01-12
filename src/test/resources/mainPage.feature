@debug @loginRelated
Feature: main page
  Scenario: check that home page exist with logo and login link
    Given I navigate to home page
    Then logo image should be displayed
    And login link should be displayed
