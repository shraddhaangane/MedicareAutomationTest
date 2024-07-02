package com.medicare.login;

import static com.medicare.helper.Constants.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.medicare.handlers.CommonHandler;
import com.medicare.handlers.LoginHandler;


public class LoginTest {
	CommonHandler commonHandler = new CommonHandler();
	LoginHandler loginHandler = new LoginHandler();

	// Verify login page with valid user details
	@Test(description = "Verify login page with valid user details")
	public void verifyLoginPageWithValidUserDetails() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, EMAIL, PASSWORD);

		driver.close();
	}


	// Verify login page with empty email address
	@Test(description = "Verify login page with empty email address")
	public void verifyLoginPageWithEmptyEmailAddress() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, EMPTY_STR, PASSWORD);

		String emailCheckExpectedMessage = "Please enter your email!";
		WebElement emailIsBlank = driver.findElement(By.id("username-error"));
		String emailIsBlankActualMessage = emailIsBlank.getText();
		System.out.println("ExpectedErrorMessage==> " + emailCheckExpectedMessage);
		driver.close();
		Assert.assertEquals(emailCheckExpectedMessage, emailIsBlankActualMessage);
		
		

	}

	// Verify login page with empty password
	@Test(description = "Verify login page with empty password")
	public void verifyLoginPageWithEmptyPassword() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, EMAIL, EMPTY_STR);

		String passwordCheckExpectedMessage = "Please enter your password!";
		WebElement passwordIsBlank = driver.findElement(By.id("password-error"));
		String passwordIsBlankActualMessage = passwordIsBlank.getText();
		System.out.println("ExpectedErrorMessage==> " + passwordCheckExpectedMessage);
		driver.close();
		Assert.assertEquals(passwordCheckExpectedMessage, passwordIsBlankActualMessage);
		
	}

	// Verify login page with invalid email address and valid password
	@Test(description = "Verify login page with invalid email address and valid password")
	public void verifyLoginPageWithInvalidEmailAddressandvalidPassword() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, INVALID_EMAIL, PASSWORD);

		String invalidEmailCheckExpectedMessage = "Username and Password is invalid!";
		WebElement invalidEmail = driver.findElement(By.xpath("//div[contains(text(),'Username and Password is invalid!')]"));
		String invalidEmailActualMessage = invalidEmail.getText();
		System.out.println("ExpectedErrorMessage==> " + invalidEmailCheckExpectedMessage);
		driver.close();
		Assert.assertEquals(invalidEmailCheckExpectedMessage, invalidEmailActualMessage);
		
	}

	// Verify login page with User name and invalid Password 
	@Test(description = "Verify login page with invalid Username and invalid password")
	public void verifyLoginPageWithInvalidUserNameandInvalidPassword() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, INVALID_EMAIL, INVALID_PASSWORD);

		String invalidUserandPasswordExpectedMessage = "Username and Password is invalid!";
		WebElement invalidMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]"));
		String invalidEmailActualMessage = invalidMessage.getText();
		System.out.println("ExpectedErrorMessage==> " + invalidUserandPasswordExpectedMessage);
		driver.close();
		Assert.assertEquals(invalidUserandPasswordExpectedMessage, invalidEmailActualMessage);
		
	}
    //Verify Register here link on Login Page
	@Test(description = "Verify Register here link on Login Page")
	public void verifyRegisterHereLinkonLoginPage() {
	WebDriver driver = commonHandler.homePage();
	driver.findElement(By.linkText("Login")).click();
	driver.findElement(By.xpath("//div[@class='text-right']//a[@href='/medicare/membership']")).click();
	driver.close();
	
	}
	
}


