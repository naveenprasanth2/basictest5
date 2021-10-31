package test1Selenium;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AllTest {
	@Test
	public void test() throws InterruptedException {
		String chromePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		driver.findElements(By.cssSelector("input.radioButton")).stream()
				.filter(x -> x.getAttribute("value").equals("radio1")).findAny().ifPresent(x -> x.click());
		driver.findElement(By.cssSelector("input#autocomplete")).sendKeys("Ind");
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30))
				.pollingEvery(Duration.ofSeconds(10)).ignoring(Exception.class);
		WebElement foo = wait.until(new Function<WebDriver, WebElement>() {

			@Override
			public WebElement apply(WebDriver t) {
				if (t.findElement(By.xpath("//li[@class='ui-menu-item'] //div")).isDisplayed()) {
					return t.findElement(By.xpath("//li[@class='ui-menu-item'] //div"));
				} else {
					return null;
				}
			}
		});

		driver.findElements(By.xpath("//li[@class='ui-menu-item'] //div")).stream()
				.filter(x -> x.getText().equalsIgnoreCase("indonesia")).findAny().ifPresent(x -> x.click());
		WebElement sele = driver.findElement(By.cssSelector("select#dropdown-class-example"));
		Select select = new Select(sele);
		select.getOptions().stream().filter(x -> x.getText().equals("Option2")).findAny().ifPresent(x -> x.click());
		Thread.sleep(2000);
		select.selectByValue("option1");
		driver.findElement(By.cssSelector("input#checkBoxOption3")).click();
		driver.findElement(By.cssSelector("button#openwindow")).click();
		winSwitch(driver);
		driver.findElement(By.cssSelector("a#opentab")).click();
		winSwitch(driver);
		driver.findElement(By.cssSelector("input#name")).sendKeys("naveen");
		driver.findElement(By.cssSelector("input#alertbtn")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().dismiss();
		driver.findElement(By.cssSelector("input#name")).sendKeys("naveen");
		driver.findElement(By.cssSelector("input#confirmbtn")).click();
		System.out.println(driver.switchTo().alert().getText());
		driver.switchTo().alert().accept();
		driver.findElements(By.xpath("//table[@name='courses'] //tr//td[3]")).stream()
				.filter(x -> x.getText().equals("0")).findAny()
				.ifPresent(x -> x.findElement(By.xpath("preceding-sibling::td[1]")).click());
		driver.findElements(By.xpath("//div[@class='tableFixHead'] //td[3]")).stream()
				.filter(x -> x.getText().equals("Pune")).findAny()
				.ifPresent(x -> x.findElement(By.xpath("following-sibling::td[1]")).click());
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.cssSelector("button#mousehover"))).perform();
		act.keyDown(Keys.CONTROL).perform();
		act.moveToElement(driver.findElement(By.cssSelector("div.mouse-hover-content a"))).click().perform();
		act.keyUp(Keys.CONTROL).perform();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scroll(0,1500)");
		WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait1.until(ExpectedConditions
				.frameToBeAvailableAndSwitchToIt(driver.findElement(By.cssSelector("iframe#courses-iframe"))));
		driver.findElement(By.xpath("//a[text()='Courses']")).click();
		driver.switchTo().parentFrame();
		driver.findElement(By.cssSelector("input#checkBoxOption3")).click();
		driver.quit();

	}

	public static void winSwitch(WebDriver driver) throws InterruptedException {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> iter = windows.iterator();
		String parent = iter.next();
		String child = iter.next();
		driver.switchTo().window(child);
		driver.findElement(By.xpath("//a[text()='Courses']")).click();
		Thread.sleep(3000);
		driver.close();
		driver.switchTo().window(parent);
	}
}
