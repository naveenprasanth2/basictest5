package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.Keys;

import baseSteps.BaseSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pageObjects.HomePageApi;
import pojo.GetCourses;

public class RestAssuredTest extends BaseSteps {

	private BaseSteps baseSteps;
	private HomePageApi homepageapi;
	private RequestSpecification request;
	private ResponseSpecification responseSpec;
	private Response response;
	private String accessToken;

	public RestAssuredTest() {
		this.baseSteps = new BaseSteps();
		this.homepageapi = new HomePageApi();
	}

	@Given("I initialize the browsers")
	public void i_initialize_the_browser() throws IOException {
		propertiesFileReader();
		driver = baseSteps.getDriver(prop.getProperty("browser"));
	}

	@Given("I open the google website")
	public void i_open_the_google_website() throws IOException {
		driver.get(prop.getProperty("apiurl"));
	}

	@Given("I enter all the required values")
	public void i_enter_all_the_required_values() {
		driver.findElement(homepageapi.email()).sendKeys("femoxa5165");
		driver.findElement(homepageapi.email()).sendKeys(Keys.ENTER);
		driver.findElement(homepageapi.password()).sendKeys("Summa1234@");
		driver.findElement(homepageapi.password()).sendKeys(Keys.ENTER);
	}

	@Given("create a spec builder")
	public void create_a_spec_builder() throws InterruptedException {
		Thread.sleep(5000);
		String url = driver.getCurrentUrl();
		String code = url.split("=")[1].split("&")[0];
		request = new RequestSpecBuilder().setUrlEncodingEnabled(false).addQueryParam("code", code)
				.addQueryParams(baseSteps.queryParams()).setContentType(ContentType.JSON).build();
		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}

	@Then("I get the values from the response")
	public void i_get_the_values_from_the_response() {

		response = given().spec(request).when().post("https://www.googleapis.com/oauth2/v4/token").then()
				.spec(responseSpec).extract().response();
	}

	@Then("I validate the values")
	public void i_validate_the_values() {
		accessToken = jsonValue(response.asString(), "access_token");
		GetCourses gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		gc.getCourses().getWebAutomation().stream().filter(x -> x.getCourseTitle().equals("Cypress")).findAny()
				.ifPresent(x -> System.out.println(x.getPrice()));

	}

}
