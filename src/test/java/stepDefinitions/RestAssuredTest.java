package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.Keys;

import baseSteps.BaseSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.parsing.Parser;
import pageObjects.HomePageApi;
import pojo.GetCourses;

public class RestAssuredTest extends BaseSteps {

	private BaseSteps baseSteps;
	private HomePageApi homepageapi;
	private RequestSpecBuilder request;
	private String accessToken;
	private HashMap<String,String> query;

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

	@Then("I get the values from the response")
	public void i_get_the_values_from_the_response() throws InterruptedException {
		Thread.sleep(5000);
		String url = driver.getCurrentUrl();
		String code = url.split("=")[1].split("&")[0];
		afterTestConditions();
		String responseCode = given().log().all().urlEncodingEnabled(false).queryParam("code", code)
				.queryParams(baseSteps.queryParams()).when()
				.post("https://www.googleapis.com/oauth2/v4/token").then().log().all().extract().response().asString();
		accessToken = jsonValue(responseCode, "access_token");
	}

	@Then("I validate the values")
	public void i_validate_the_values() {
		// Write code here that turns the phrase above into concrete actions
		GetCourses gc = given().queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON).when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourses.class);
		gc.getCourses().getWebAutomation().stream().filter(x -> x.getCourseTitle().equals("Cypress")).findAny()
				.ifPresent(x -> System.out.println(x.getPrice()));

	}

}
