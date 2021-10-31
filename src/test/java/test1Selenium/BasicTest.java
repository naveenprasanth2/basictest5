package test1Selenium;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasicTest {
	@Test
	public void test() throws IOException{

	String chromePath = System.getProperty("user.dir") + "\\src\\main\\java\\resources\\chromedriver.exe";
	String screenPath = System.getProperty("user.dir") + "\\src\\main\\java\\screenshots\\test1.png";
	String reportPath = System.getProperty("user.dir") + "\\src\\main\\java\\reports\\test1.html";


	WebDriverManager.chromedriver().setup();
	DesiredCapabilities dc = new DesiredCapabilities();
	dc.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
	dc.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);

	ChromeOptions co = new ChromeOptions();
	co.setBinary(chromePath);
	co.addArguments("--headless");
	co.addArguments("--window-size=1920,1080");
	co.addArguments("--disable-infobars");
	co.merge(dc);

	ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
	spark.config().setDocumentTitle("Regression");
	spark.config().setReportName("Regression Phase1");

	ExtentReports report = new ExtentReports();
	report.attachReporter(spark);
	report.setSystemInfo("tester", "Naveen");

	ExtentTest test = report.createTest("test1");

	WebDriver driver = new ChromeDriver(co);
	driver.get("https://rahulshettyacademy.com/AutomationPractice/");

	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scroll(0,500)");
	js.executeScript("document.querySelector('div.tableFixHead').scrollTop=5000");

	TakesScreenshot sc = (TakesScreenshot) driver;
	File src = sc.getScreenshotAs(OutputType.FILE);
	FileUtils.copyFile(src, new File(screenPath));

	test.addScreenCaptureFromPath(screenPath);
	report.flush();
	driver.quit();


	}
}
