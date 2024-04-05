package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

import static org.testng.Assert.assertEquals;

public class Products {
//    public int StatusCode;
    public RequestSpecification httpRequest;
    public Response response;
    public int responseCode;
    public ResponseBody body;
    JSONObject requestParams;

    @Given("I hit the URL of GET products API endpoint")
    public void i_hit_the_url_of_get_products_api_endpoint() {

        RestAssured.baseURI = "https://fakestoreapi.com/";

    }

    @When("I pass the URL of products in the request")
    public void i_pass_the_url_of_products_in_the_request() {

        httpRequest = RestAssured.given();
        response = httpRequest.get("products");

    }

    @Then("I receive the response code as {int}")
    public void i_receive_the_response_code_as(Integer int1) {

        responseCode = response.getStatusCode();
        assertEquals(responseCode, int1);
    }

    @Then("I verify the price of the first product is {}")
    public void i_verify_the_price_of_the_first_product_is(String rate) {

        body = response.getBody();
//        String responseBody = body.asString();
        JsonPath jsonPath = response.jsonPath();
        String s = jsonPath.getJsonObject("rating[0].rate").toString();
        assertEquals(rate, s);

    }

    @Given("I hit the URL of POST products API endpoint")
    public void i_hit_the_url_of_post_products_api_endpoint() {

        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = RestAssured.given();
        requestParams = new JSONObject();

    }

    @And("I pass the request body of product title {}")
    public void i_pass_the_request_body_of_product_title_shoes(String title) {

        requestParams.put("title", title);
        requestParams.put("price", "7");
        requestParams.put("description", "Air Penny IV");
        requestParams.put("image", "https://i.pravatar.cc");
        requestParams.put("category", "Basketball");

        httpRequest.body(requestParams.toJSONString());
        Response response = httpRequest.post("products");
        body = response.getBody();

        System.out.println(response.getStatusLine());
        System.out.println(body.asString());
    }

    @Then("I receive the response body with id as {}")
    public void iReceiveTheResponseBodyWithIdAsId(String id) {

        JsonPath jsonPath = response.jsonPath();
        String s = jsonPath.getJsonObject("id").toString();
        assertEquals(id, s);

    }

    @Given("I hit the URL of PUT products API endpoint")
    public void iHitTheURLOfPUTProductsAPIEndpoint() {

        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = RestAssured.given();
        requestParams = new JSONObject();

    }

    @When("I pass the URL of products in the request with {}")
    public void iPassTheURLOfProductsInTheRequestWithProductNumber(String productNumber) {

//        httpRequest = RestAssured.given();

        requestParams.put("title", "test update");
        requestParams.put("price", "8");
        requestParams.put("description", "Air Penny Foamposites");
        requestParams.put("image", "https://i.pravatar.cc");
        requestParams.put("category", "Casual");

        httpRequest.body(requestParams.toJSONString());
        response = httpRequest.put("products/" + productNumber);

        body = response.getBody();
        JsonPath jsonPath = response.jsonPath();
        String s = jsonPath.getJsonObject("id").toString();
        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

    }

    @Given("I hit the URL of DELETE products API endpoint")
    public void iHitTheURLOfDELETEProductsAPIEndpoint() {

        RestAssured.baseURI = "https://fakestoreapi.com/";
        httpRequest = RestAssured.given();
        requestParams = new JSONObject();
    }

    @When("I pass the URL of products in the DELETE request with {}")
    public void iPassTheURLOfProductsInTheDELETERequestWithProductNumber(String productNumber) {

        response = httpRequest.delete("products/" + productNumber);

    }
}
