Feature: Search a city to find popular places

  @search-feature
  Scenario Outline: invalid city
    Given the user is in MainActivity
    When the user types a non existing city <city>
    And the user clicks on the search button
    Then an error dialog is shown

    Examples:
      | city          |
      | sijdisfiohdaf |

  @search-feature
  Scenario Outline: valid city
    Given the user is in MainActivity
    When the user types an existing city <city>
    And the user clicks on the search button
    Then the app navigates to the PlacesListActivity

    Examples:
    | city   |
    | London |