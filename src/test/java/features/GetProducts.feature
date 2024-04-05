Feature: Get all products from the API

  Scenario: Verify the GET API for the products
    Given I hit the URL of GET products API endpoint
    When I pass the URL of products in the request
    Then I receive the response code as 200

  Scenario Outline: Verify the rate of the first product is correct
    Given I hit the URL of GET products API endpoint
    When I pass the URL of products in the request
    Then I verify the price of the first product is <FirstProductRate>
    Examples:
      | FirstProductRate |
      | 3.9              |