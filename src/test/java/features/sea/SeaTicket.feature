@ferry @payment
Feature: Buy Ferry Tickets

  Background: go to ferry home page
    Given go to homepage
    #And change the language of site to tr
    And open to Sea main page


  @Regression @smoke
  Scenario: Buy budo ferry ticket
    When select where from as "Bursa"
    And select where to as "eminönü"
    And select departure date
    And click search button
    And select random ferry trip and seat for budo
    And click continue button on list pages
    And enter passenger informations for sea
    And enter card informations real
    And click pay button
    #Then assertion şimdilik bende :D

  @Regression @smoke
  Scenario: Buy ido ferry ticket
    When select where from as "İstanbul"
    And select where to as "Yalova"
    And select departure date
    And click search button
    And select random ferry trip and seat for ido
    And click continue button on list pages
    And enter passenger informations for sea
    And enter card informations real
    And click pay button



  @Regression @smoke
  Scenario: Buy ido ferry ticket with car
    When select where from as "İstanbul"
    And select where to as "Yalova"
    And select departure date
    And select passenger type as adult and count is 1
    And click search button
    And select random ferry trip and seat for ido
    And click continue button on list pages
    And enter passenger informations for sea
    And enter card informations real
    And click pay button


