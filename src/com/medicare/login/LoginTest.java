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

		String emailCheck = "Please enter your email!";
		WebElement emailIsBlank = driver.findElement(By.id("username-error"));
		driver.close();
		Assert.assertEquals(emailCheck, emailIsBlank.getText());
		
		

	}

	// Verify login page with empty password
	@Test(description = "Verify login page with empty password")
	public void verifyLoginPageWithEmptyPassword() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, EMAIL, EMPTY_STR);

		String passwordCheck = "Please enter your password!";
		WebElement passwordIsBlank = driver.findElement(By.id("password-error"));
		driver.close();
		Assert.assertEquals(passwordCheck, passwordIsBlank.getText());
		
	}

	// Verify login page with invalid email address
	@Test(description = "Verify login page with invalid email address")
	public void verifyLoginPageWithInvalidEmailAddress() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, INVALID_EMAIL, EMPTY_STR);

		String invalidEmailCheck = "Please enter a valid email address!";
		WebElement invalidEmail = driver.findElement(By.id("username-error"));
		driver.close();
		Assert.assertEquals(invalidEmailCheck, invalidEmail.getText());
		
	}

	// Verify login page with User name and invalid Password 
	@Test(description = "Verify login page with invalid Username and invalid password")
	public void verifyLoginPageWithInvalidUserNameandPassword() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, INVALID_EMAIL, INVALID_PASSWORD);

		String invalidUsernameandPasswordCheck = "Username and Password is invalid!";
		WebElement invalidMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-danger')]"));
		driver.close();
		Assert.assertEquals(invalidUsernameandPasswordCheck, invalidMessage.getText());
		
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


