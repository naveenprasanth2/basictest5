package stepDefinitions;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import baseSteps.BaseSteps;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;

public class RahulShettyLogin extends BaseSteps{

	private WebDriver driver;
	private HomePage homePage;
	private LoginPage loginPage;
	private BaseSteps baseSteps;

	public RahulShettyLogin() {
		this.loginPage = new LoginPage();
		this.homePage = new HomePage();
		this.baseSteps = new BaseSteps();
	}

	@Given("I initialize the browser")
	public void i_initialize_the_browser() throws IOException {
		propertiesFileReader();
		driver = baseSteps.getDriver(prop.getProperty("browser"));
	}

	@Given("I open the RahulShetty website")
	public void i_open_the_rahul_shetty_website() {
		driver.get(prop.getProperty("url"));
	}

	@Given("I click on the loginButton")
	public void i_click_on_the_login_button() {
		driver.findElement(homePage.loginButton()).click();
	}

	@Given("I enter the credentials {string} and {string}")
	public void i_enter_the_credentials_and(String userid, String password) {
		driver.findElement(loginPage.userName()).sendKeys(userid);
		driver.findElement(loginPage.password()).sendKeys(password);
	}

	@When("I click on login button")
	public void i_click_on_login_button() {
		driver.findElement(loginPage.login()).click();
	}

	@Then("System throws error")
	public void system_throws_error() {
		baseSteps.afterTestConditions();
	}

}