
Feature:Filtering Table Actions

  Scenario Outline:Hidden Manage Filter
    Given the "<usertype>" user is logged in
    When the user navigates to "Fleet" "Vehicles" page
    Then Manage filters should be hidden
    Examples:
      | usertype     |
      | driver       |
      | sales manager |
      | store manager |


Scenario Outline: After Clicking filter , manage Filter options appears
   Given the "<usertype>" user is logged in
   When the user navigates to "Fleet" "Vehicles" page
  And the user click the filter button
  Then Manage filter options should be displayed after clicking
   Examples:
     | usertype     |
     | driver       |
     | sales manager |
     | store manager |

  Scenario Outline: User can apply filtering for each of the data columns on the page
    Given the "<usertype>" user is logged in
    When the user navigates to "Fleet" "Vehicles" page
    And the user click the filter button
    And the user click on manage filter button
    Then User can apply filtering for each of the "<dataColumns>"  on the page

    Examples:
      | usertype      | dataColumns         |
      | driver        | Driver              |
      | driver        | Tags                |
#        | driver        | Seat Number         |
#        | driver        | First Contract Date |
#        | store manager | Driver              |
#        | store manager | Tags                |
#        | store manager | Seat Number         |
#        | store manager | First Contract Date |
#        | sales manager | Driver              |
#        | sales manager | Tags                |
#        | sales manager | Seat Number         |
#        | sales manager | First Contract Date |
   @haci
  Scenario Outline: using reset button
    Given the "<usertype>" user is logged in
    When the user navigates to "Fleet" "Vehicles" page
    And the user click the filter button
    And the user click on manage filter button
    And select all boxes
    Then all check boxes are selected
    When the user click on reset button
    Then User can  remove all filterings by using the reset button
    Examples:
      | usertype     |
      | driver       |
      | sales manager |
      | store manager |
  @wip
    Scenario Outline:  multiple filtering of columns
      Given the "<usertype>" user is logged in
      When the user navigates to "Fleet" "Vehicles" page
      And the user click the filter button
      And the user click on manage filter button
      And the user click on multiple options
      When the user sends values for the Licence Plate and Tags
      Then the application should be able to support multiple filtering of columns


      Examples:
        | usertype     |
        | driver       |
        | sales manager |
        | store manager |

