Feature: Logout functionality

  Scenario: Logout account from system
    Given user is on system
    When user click icon menu button
    And user click logout button
    Then user verify logout process