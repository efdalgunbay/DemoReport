Feature: US_002_Login_Tests

  Background:
    Given i go home

  @Login
  Scenario: TC_201 Positive Login
    And Email Button Click

  @Login
  Scenario: EN_201 Negative Login
    And Email Button Clicks