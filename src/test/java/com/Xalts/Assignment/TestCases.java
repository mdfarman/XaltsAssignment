package com.Xalts.Assignment;

import org.testng.annotations.Test;

import com.github.javafaker.Faker;

public class TestCases extends BaseTest
{
	static String randomEmail;
	final private String password = "P@ssword123";
	@Test(description = "Verify that a user can successfully sign up with a valid email and password.")
	public void Id1() {
		randomEmail = new Faker().internet().emailAddress();
		
		Utils.fluentwait("//button[@type='button' and text()='Sign In']");
		Utils.click("//button[@type='button' and text()='Sign In']");
		
		Utils.fluentwait("(//*[@id='outlined-basic'])[1]");
		//Enter email id
		Utils.enterValue("(//*[@id='outlined-basic'])[1]", randomEmail);
		//Enter password
		Utils.enterValue("(//*[@id='outlined-basic'])[2]", password);
		//Enter conform password
		Utils.enterValue("(//*[@id='outlined-basic'])[3]", password);
		
		Utils.click("//*[text()='Sign Up']");
		Utils.fluentwait("//*[text()='Open Capital Network']");
		Utils.ValidateText("//*[text()='Open Capital Network']", "Open Capital Network");
	}
	
	
	@Test(description = "Verify that the system shows an error message when the user enters an invalid email format.")
	public void Id2() {
		Utils.fluentwait("//button[@type='button' and text()='Sign In']");
		Utils.click("//button[@type='button' and text()='Sign In']");
		
		String randomEmail = new Faker().internet().emailAddress();
		String invalidEmail = randomEmail.replace("@", "");
		Utils.fluentwait("(//*[@id='outlined-basic'])[1]");
		//Enter email id
		Utils.enterValue("(//*[@id='outlined-basic'])[1]", invalidEmail);
		Utils.enterValue("(//*[@id='outlined-basic'])[2]", password);
		Utils.enterValue("(//*[@id='outlined-basic'])[3]", password);
		
		Utils.validateButtonIsDisabled("//*[text()='Sign Up']");
	}
	
	
	@Test(description = "Verify that the system displays an error message when the user enters a password that does not meet the criteria")
	public void Id3() {
		
		Faker faker = new Faker();
		Utils.fluentwait("//button[@type='button' and text()='Sign In']");
		Utils.click("//button[@type='button' and text()='Sign In']");
		String randomEmail = faker.internet().emailAddress();
		String invalidPassword =  faker.name().firstName();
		
		Utils.fluentwait("(//*[@id='outlined-basic'])[1]");
		Utils.enterValue("(//*[@id='outlined-basic'])[1]", randomEmail);
		//Enter password
		Utils.enterValue("(//*[@id='outlined-basic'])[2]", invalidPassword);
		String passwordInvalidMsg = "Password must contain atelast one lowercase letter, uppercase letter, number and special character and be a minimum of 8 characters in length";
		Utils.ValidateText("(//*[@id='outlined-basic-helper-text'])[1]", passwordInvalidMsg);
		
		//Enter conform password
		String ConfirmPasswordInvalidMsg = "Passwords do not match"; 
		Utils.enterValue("(//*[@id='outlined-basic'])[3]", invalidPassword+"123");
		Utils.ValidateText("(//*[@id='outlined-basic-helper-text'])[2]", ConfirmPasswordInvalidMsg);
		Utils.validateButtonIsDisabled("//*[text()='Sign Up']");
	}
	
	
	
	@Test(description = "Verify that the system shows an error message when the user tries to sign up with an email that already exists in the system.")
	public void Id4() throws InterruptedException {
		randomEmail = new Faker().internet().emailAddress();
		
		Utils.fluentwait("//button[@type='button' and text()='Sign In']");
		Utils.click("//button[@type='button' and text()='Sign In']");
		
		Utils.fluentwait("(//*[@id='outlined-basic'])[1]");
		//Enter email id
		Utils.enterValue("(//*[@id='outlined-basic'])[1]", randomEmail);
		//Enter password
		Utils.enterValue("(//*[@id='outlined-basic'])[2]", password);
		//Enter conform password
		Utils.enterValue("(//*[@id='outlined-basic'])[3]", password);
		
		Utils.click("//*[text()='Sign Up']");
		Utils.fluentwait("//*[text()='Open Capital Network']");
		Utils.ValidateText("//*[text()='Open Capital Network']", "Open Capital Network");
		//click for signout
		Utils.click("//button[text()='Sign Out']");
		Utils.fluentwait("//button[@type='button' and text()='Sign In']");
		Utils.click("//button[@type='button' and text()='Sign In']");
		
		Utils.fluentwait("(//*[@id='outlined-basic'])[1]");
		//Enter email id
		Utils.enterValue("(//*[@id='outlined-basic'])[1]", randomEmail);
		//Enter password
		Utils.enterValue("(//*[@id='outlined-basic'])[2]", password);
		//Enter conform password
		Utils.enterValue("(//*[@id='outlined-basic'])[3]", password);
		
		Utils.click("//*[text()='Sign Up']");
		Thread.sleep(2000);
		Utils.ValidateAlertMsg("Provided E-Mail is already in use");
		
	}
	
	@Test(description = "verify that the Node ID field correctly validates the input format and accepts valid entries")
	public void Id5() {
		Utils.fluentwait("//button[@type='button' and text()='Sign In']");
		Utils.click("//button[@type='button' and text()='Sign In']");
		
		Utils.fluentwait("//button[contains(text(),'Already have an account')]");
		Utils.click("//button[contains(text(),'Already have an account')]");
		
		Utils.validateBorderColor("(//*[@id='outlined-basic'])[1]/following-sibling::fieldset","rgb(211, 47, 47)");

		Utils.enterValue("(//*[@id='outlined-basic'])[1]", "testusername706@gmail.com");
		Utils.validateBorderColor("(//*[@id='outlined-basic'])[2]/following-sibling::fieldset","rgb(211, 47, 47)");

		Utils.enterValue("(//*[@id='outlined-basic'])[2]", "Password@123");
		Utils.click("//button[text()='Sign In']");
		
		Utils.fluentwait("//*[text()='Open Capital Network']");
		Utils.ValidateText("//*[text()='Open Capital Network']", "Open Capital Network");
		
		Utils.click("//button[text()='Get Started']");
		
		Utils.fluentwait("//*[text()='Onboard OCN Node']");
		Utils.click("//*[text()='Onboard OCN Node']");
		
		Utils.validateInputBoxIsEmpty("(//*[@id='outlined-basic'])[1]");
		Utils.validateInputBoxIsEmpty("(//*[@id='outlined-basic'])[2]");
		Utils.validateBorderColor("(//*[@id='outlined-basic'])[1]/following-sibling::fieldset","rgb(211, 47, 47)");
		Utils.validateButtonIsDisabled("//button[text()='+ Add Node ']");
		Utils.validateButtonIsDisabled("//button[text()='Next']");
		
		Faker faker= new Faker();
		String number= faker.number().digit();
		Utils.enterValue("(//*[@id='outlined-basic'])[1]", "NodeID-"+number);
        String randomIpAddress = faker.internet().ipV4Address();
        Utils.enterValue("(//*[@id='outlined-basic'])[2]", randomIpAddress);
        Utils.validateButtonIsEnabled("//button[text()='+ Add Node ']");
        Utils.click("//button[text()='+ Add Node ']");
        Utils.validateButtonIsEnabled("//button[text()='Next']");
		
	}
}