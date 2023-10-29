Feature: Hotel search

  Background: Go to homepage
    Given go to homepage
    And open to Hotel main page

  @smoke @regression @search @hotel
  Scenario: Hotel search with location
    When search "Adana" with location
    And click search button
    Then check list page opened


  @smoke @regression @search @hotel
  Scenario: Hotel search with Hotel name
    When search "Bursa Park Apart" with location
    And click search button
    Then check list page opened


  @smoke @regression @search @hotel
  Scenario: Hotel search with Themes
    When search "Family Hotel" with location
    And click search button
    Then check list page opened