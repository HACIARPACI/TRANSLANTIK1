
Feature:As a user, I should be able to filter table
  @wip
  Scenario Outline:Hidden
    Given the "<usertype>" user is logged in
    When the user navigates to "Fleet" "Vehicles" page
    Then Manage filters should be hidden
    Examples:
      | usertype     |
      | driver       |
      | sales manager |
      | store manager |


Scenario Outline: Manage filter options should be displayed once the user clicks on the filter button
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
    #        | driver        | Tags                |
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

  Scenario Outline: using reset button
    Given the "<usertype>" user is logged in
    When the user navigates to "Fleet" "Vehicles" page
    And select all boxes
    When the user click on reset button
    Then User can  remove all filterings by using the reset button
    Examples:
      | usertype     |
      | driver       |
      | sales manager |
      | store manager |