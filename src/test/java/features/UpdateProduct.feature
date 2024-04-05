Feature: Update product using PUT API

  Scenario Outline: Validate PUT product API status code works correctly
    Given I hit the URL of PUT products API endpoint
    When I pass the URL of products in the request with <ProductNumber>
#    And I pass the request body of PUT API
    Then I receive the response code as 200
    Examples:
      | ProductNumber |
      | 6             |