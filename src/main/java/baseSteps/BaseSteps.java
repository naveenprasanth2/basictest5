package baseSteps;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;

public class BaseSteps {
	protected WebDriver driver;
	protected Properties prop;
	protected FileReader reader;
	private static final Integer TIMEOUTS = 10;

	public WebDriver getDriver(String browser) {

		switch (browser) {
		case "Chrome":
			WebDriverManager.chromedriver().setup();
			this.driver = new ChromeDriver();
			break;
		case "Firefox":
			WebDriverManager.firefoxdriver().setup();
			this.driver = new FirefoxDriver();
			break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			this.driver = new EdgeDriver();
			break;
		case "IE":
			WebDriverManager.iedriver().setup();
			this.driver = new InternetExplorerDriver();
			break;
		default:
			System.out.println("Enter valid data");
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		return this.driver;
	}

	public void propertiesFileReader() throws IOException {
		reader = new FileReader(System.getProperty("user.dir") + "\\config\\data.properties");
		prop = new Properties();
		prop.load(reader);
	}
	public void waitUntil(WebDriver driver, WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUTS));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy((By) element));
	}

	public void afterTestConditions() {
		driver.quit();
	}
	
	public String jsonValue(String value , String path) {
		JsonPath js = new JsonPath(value);
		return js.getString(path);
	}
	
	public Map<String,String> queryParams(){
		HashMap<String,String> queryValue = new HashMap<>();
		queryValue.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
		queryValue.put("client_secret", "erZOWM9g3UtwNRj340YYaK_W");
		queryValue.put("redirect_uri", "https://rahulshettyacademy.com/getCourse.php");
		queryValue.put("grant_type", "authorization_code");
		return queryValue;
	}
}
