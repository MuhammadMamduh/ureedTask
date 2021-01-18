Feature: User Login
  Validate the [Login] functionality.

  Scenario Outline: User Login
    Given the user in the home page
    When The User clicks on Login button
    And The User enters his credentials "<email>" & "<password>"
    Then The UserProfilePage should get displayed successfully

    Examples:
      | email | password |
      | muhammad.mma.oz@gmail.com | A1b2c3d4@ |
      | muhammad.mma.oz@gmail.com | wrongPassword |
