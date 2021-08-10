Feature: Filter options

  Scenario Outline: Hidden manage filter
    Given the "<usertype>"user is in the login page
       Examples:
         | usertype     |
         | driver       |
         | salesmanager |
         | storemanager |
    When the user navigates to "Fleet" "Vehicles" page
    Then Manage filters should be hidden