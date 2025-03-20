package com.Xalts.Assignment;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

public class Utils extends BaseTest{
	public synchronized static void scrollToElement(String locator) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement element = driver.findElement(By.xpath(locator));
			js.executeScript("arguments[0].scrollIntoView(true);", element);
		} catch (Exception e) {
			System.out.println("unable to scroll to element");
		}
	}
	
//	done
	public synchronized static void click(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		try {
			element.click();
		} catch (Exception e) {
			System.out.println("unable to click on element");
			e.printStackTrace();
		}
	}

	public static String findElementGetByAttributeValue(String locatorXpath) {
		WebElement element = driver.findElement(By.xpath(locatorXpath));
		String validationMessage = element.getAttribute("validationMessage");
		return validationMessage;
	}

	//done
	public static void ValidateText(String locator, String ExpectedUiValue) {
		String uivalue = driver.findElement(By.xpath(locator)).getText();
		Assert.assertEquals(uivalue, ExpectedUiValue,"Expected value is not matched with ui value");
		
	}

	//done
	public static void enterValue(String LocatorXpath, String value) {
		
		 driver.findElement(By.xpath(LocatorXpath)).clear();
		 driver.findElement(By.xpath(LocatorXpath)).sendKeys(value);
	}
	
	//done
	public static void fluentwait(String locator) {
		Wait<WebDriver> wait = new FluentWait<>(driver)
								.withTimeout(Duration.ofSeconds(5))
								.pollingEvery(Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator)));			
		
	}

	//done
	public static void validateButtonIsDisabled(String loctor) {
		Boolean button = driver.findElement(By.xpath(loctor)).isEnabled();
		Assert.assertFalse(button);
		
	}
	public static void validateButtonIsEnabled(String loctor) {
		Boolean button = driver.findElement(By.xpath(loctor)).isEnabled();
		Assert.assertTrue(button);
		
	}

	public static void ValidateAlertMsg(String msg) {
		
		String text = driver.switchTo().alert().getText();
		if(!(text.equals(msg))){
			Assert.assertTrue(false);
		}
		
		driver.switchTo().alert().accept();
		
	}

	public static void validateInputBoxIsEmpty(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		String validationMessage = element.getAttribute("value");
		if(!validationMessage.isEmpty()) {
			Assert.assertFalse(true);
		}
		
	}

	public static void validateBorderColor(String locator,String Expected) {
		WebElement element = driver.findElement(By.xpath(locator));
		String borderColor = element.getCssValue("border-color");
		if(!borderColor.equals(Expected)) {
			Assert.assertFalse(true);
		}

	}
}
	
