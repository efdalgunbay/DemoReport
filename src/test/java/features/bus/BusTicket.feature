@smoke @regression @payment
Feature: Buy bus ticket

  Background: go to ferry home page
    Given go to homepage

  Scenario: Buy one way, 1 person bus journey
    When select where from as "İstanbul"
    And select where to as "Ankara"
    And select departure date
    And click search button
    And select random bus trip
    And select random seat for male
    And select random seat for female
    And click continue button on list pages
    And enter passenger informations for bus
    And enter card informations dummy
    And click pay button
    And set pnr code and partner name
    And go to trips page
    And insert ticket informations and search ticket
    And click on the cancel button in the ticket search
    And click on cancel button in the pop-up
    Then check succes pop-up is displayed






