package pageObjects;

import org.openqa.selenium.By;

public class LoginPage {

	private static final By userName = By.id("email");
	private static final By password = By.id("password");
	private static final By login = By.xpath("//input[@name='commit']");
	private static final By error = By.xpath("//div[contains(@class,'auth-flash-error')]");
	
	public By userName() {
		return userName;
	}
	
	public By password() {
		return password;
	}
	
	public By login() {
		return login;
	}
	public By error() {
		return error;
	}
}
