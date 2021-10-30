package pageObjects;

import org.openqa.selenium.By;

public class HomePageApi {

	private static final By email = By.xpath("//input[@type='email']");
	private static final By password = By.name("password");
	
	public By email() {
		return email;
	}
	
	public By password() {
		return password;
	}
}
