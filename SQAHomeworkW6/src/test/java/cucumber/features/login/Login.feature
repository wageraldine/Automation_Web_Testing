Feature: Login functionality

  Scenario Outline: Login to application as valid user
    Given user is on Saucedemo login page
    When user input <username> as username
    And user input <password> as password
    And user click submit
    Then user verify <status> login result

    Examples:
      | username      | password        | status      |
      | standard_user | secret_sauce    | success     |
      | failed_login  | failed_login    | failed      |