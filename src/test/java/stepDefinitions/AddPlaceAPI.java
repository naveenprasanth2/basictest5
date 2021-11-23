package stepDefinitions;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.junit.runner.RunWith;

import baseSteps.BaseSteps;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojoConstruct.AddPlace;
import pojoConstruct.Location;

@RunWith(Cucumber.class)
public class AddPlaceAPI  extends BaseSteps{

	RequestSpecification req;
	ResponseSpecification res;
	Response rs;
	BaseSteps bs;
	AddPlace ap = new AddPlace();

	public AddPlaceAPI() {
		bs = new BaseSteps();
	}
	@Given("^Add place payload$")
	public void add_place_payload() throws Throwable {
		ap.setAccuracy(22);
		ap.setName("naveen house");
		ap.setPhone_number("(+91) 7856230389");
		ap.setAddress("29, side layout, cohen 09111");
		ap.setWebsite("http://google.com");
		ap.setLanguage("French-IN11");
		Location l = new Location();
		l.setLat(-38.2);
		l.setLng(33.7);
		ap.setLocation(l);
		req = new RequestSpecBuilder().setUrlEncodingEnabled(false).setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).addQueryParam("key", "qaclick123").build();
	}

	@When("^user calls \"([^\"]*)\" with POST http Request$")
	public void user_calls_something_with_post_http_request(String apiType) throws Throwable {
		switch (apiType) {
		case "addPlaceAPI":
			rs = given().spec(req).body(ap).when().post("/maps/api/place/add/json").then().extract().response();
			break;
		}
	}

	@Then("^the API call is success with status code \"([^\"]*)\"$")
	public void the_api_call_is_success_with_status_code_something(String strArg1) throws Throwable {
		Assert.assertEquals(200, rs.getStatusCode());
	}

	@And("^\"([^\"]*)\" in response body is \"([^\"]*)\"$")
	public void something_in_response_body_is_something(String key, String value) throws Throwable {
		String scope = jsonValue(rs.asString(), key);
		Assert.assertEquals(value, scope);
	}

}
