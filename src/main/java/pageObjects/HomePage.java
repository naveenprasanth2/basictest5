package pageObjects;

import org.openqa.selenium.By;

public class HomePage {

	private static final By loginButton = By.xpath("//a[contains(@class,'register-btn')]");
	private static final By popupClose = By.cssSelector("span#closeHeader");

	public By loginButton() {

		return loginButton;
	}

	public By popupClose() {
		return popupClose;
	}

}
