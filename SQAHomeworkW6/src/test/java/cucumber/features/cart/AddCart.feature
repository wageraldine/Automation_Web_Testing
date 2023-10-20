Feature: Add item to cart functionality

  Scenario: Add some item to cart
    Given user is on inventory page
    When user click add to cart button
    And user click cart button
    Then user is on cart page