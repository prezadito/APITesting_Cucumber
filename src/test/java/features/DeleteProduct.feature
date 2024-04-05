Feature: Delete product using DELETE API

  Scenario Outline: Validate DELETE product API status code works correctly
    Given I hit the URL of DELETE products API endpoint
    When I pass the URL of products in the DELETE request with <ProductNumber>
    Then I receive the response code as 200
    Examples:
      | ProductNumber |
      | 6             |