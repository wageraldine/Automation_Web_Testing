Feature: Checkout some items functionality

  Scenario Outline: Checkout some items with valid data
    Given user is on cart page
    When user click checkout button
    Then user is on checkout step one page
    When user input <firstname> as firstname
    And user input <lastname> as lastname
    And user input <zipcode> as zipcode
    And user click continue button
    Then user verify <status> checkout result

    Examples:
      | firstname     | lastname        | zipcode     | status     |
      | Wildan        | Adhitya         | 4074        | valid      |
      |               |                 |             | unvalid    |