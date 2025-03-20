package com.Xalts.Assignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

/**
 * Hello world!
 *
 */
public class BaseTest {
	public static WebDriver driver;
	final String USERNAME = "testusername706@gmail.com";
	final String PASSWORD = "Password@123";

	@BeforeMethod
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resource/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS );
		driver.manage().timeouts().pageLoadTimeout(2, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(1, TimeUnit.SECONDS);
		driver.get("https://xaltsocnportal.web.app/");
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
