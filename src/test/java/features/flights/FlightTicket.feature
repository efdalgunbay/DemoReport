Feature: Flight Ticket

  Background: go to flight page
    Given go to homepage
    And open to Flight main page

    Scenario: Buy one way, 1 person flight ticket
      When select where from as "İstanbul"
      And select where to as "Ankara"
      And select departure date
      And add 2 passenger dummy
      And click search button
      