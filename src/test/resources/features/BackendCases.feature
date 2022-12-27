@All @ApiTestCases
Feature: CallSign Backend testcases
  Scenario: Convert Currencies to Bolivian Boliviano
    Given User set API Key in Header
    When User hit API for Currency
    Then User validate status code 200
    When User set API Key in Header
    And User convert Currencies
    Then User again validate status code 200

  Scenario: Cryptocurrency into calls
    Given User set API Key in Header
    When User hit info Currency API
    Then User again validate status code 200

  Scenario: Retrieve first 10 Countries and print them
    Given User set API Key in Header
    When User hit for 10 Countries info Currency API
    Then User again validate status code 200
