@All @backend
Feature: Backend Scenarios

  @backend
  Scenario Outline: user wants to execute cryptocurrency mapping case
    Given user enters API key
    And user hits currency mapping API
    And user extracts the currency Ids
    And user validates status code is 200
    When user enters API key
    Then user converts currency "<sourceCurrency>" into BOB
    And user validates status code is 200

    Examples:
    | sourceCurrency |
    | BTC            |
    | USDT           |
    | ETH            |

    @backend
    Scenario: user wants to execute cryptocurrency info call
      Given user enters API key
      And user hits currency info API
      And user validates the API response
      And user validates status code is 200

  @backend
  Scenario: user wants to execute cryptocurrency info call
    Given user enters API key
    When user hits the currency info API
    Then user validates status code is 200
    And user verifies the currencies with mineable tag






