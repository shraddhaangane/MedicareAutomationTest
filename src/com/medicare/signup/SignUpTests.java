package com.medicare.signup;

import static com.medicare.helper.Constants.*;

import java.sql.SQLException;

import javax.annotation.concurrent.NotThreadSafe;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.medicare.database.DatabaseOperations;
import com.medicare.handlers.CommonHandler;
import com.medicare.handlers.SignUpHandler;

@NotThreadSafe
public class SignUpTests {
	CommonHandler commonHandler = new CommonHandler();
	SignUpHandler signUpHandler = new SignUpHandler();
	DatabaseOperations databaseOperations = new DatabaseOperations();

	// Verify signup page with valid user details
	@Test(description = "Verify signup page with valid user details")
	public void verifySignUpPageWithValidUserDetails() {
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
		driver.close();
	}

	// Verify signup page with Empty User Details
	@Test(description = "Verify signup page with Empty User Details")
	public void verifySignUpPageWithEmptyUserDetails() {
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, EMPTY_STR, EMPTY_STR, EMPTY_STR, EMPTY_STR, EMPTY_STR, EMPTY_STR);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
		driver.close();
	}

	// Verify signup page with First Name is blank
	@Test(description = "signup page with First Name is blank")
	public void verifyFirstNameIsBlank() {
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, EMPTY_STR, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		String firstNameErrorMessageExpected = "Please enter first name!";
		WebElement firstNameBlank = driver.findElement(By.id("firstName.errors"));
		String firstNameErrorMessageActual = firstNameBlank.getText();
		System.out.println("ExpectedErrorMessage==> " + firstNameErrorMessageExpected);
		driver.close();
		Assert.assertEquals(firstNameErrorMessageExpected, firstNameErrorMessageActual);

	}

	// Verify signup page with Last Name is blank
	@Test(description = "Verify signup page with Last Name is blank")
	public void verifyLastNameIsBlank() {
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, EMPTY_STR, EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		String lastNameErrorMessageExpected = "Please enter last name!";
		WebElement lastNameisblank = driver.findElement(By.id("lastName.errors"));
		String lastNameErrorMessageActual = lastNameisblank.getText();
		System.out.println("ExpectedErrorMessage==> " + lastNameErrorMessageExpected);
		driver.close();
		Assert.assertEquals(lastNameErrorMessageExpected, lastNameErrorMessageActual);

	}

	// Verify signup page with Email is blank
	@Test(description = "Verify signup page with Email is blank")
	public void verifyEmailIsBlank() {
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMPTY_STR, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		String emailErrorMessageExpected = "Please enter email address!";
		WebElement emailisBlank = driver.findElement(By.id("email.errors"));
		String emailErrorMessageActual = emailisBlank.getText();
		System.out.println("ExpectedErrorMessage==> " + emailErrorMessageExpected);
		driver.close();
		Assert.assertEquals(emailErrorMessageExpected, emailErrorMessageActual);
	}
	
	// Verify signup page with Email address is already taken
		@Test(description = "Verify signup page with Email address is already taken" )
		public void verifyEmailIsAlreadyTaken() {
			WebDriver driver = commonHandler.homePage();
			signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
			signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD,
					CONFIRM_PASSWORD);
			signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
			
			
			String emailalredytakenexpectedErrorMsg = "Email address is already taken!";
	        
	        WebElement emailalreadytakenmsg = driver.findElement(By.id("email.errors"));
	        String emailalredytakenactualErrorMsg = emailalreadytakenmsg.getText();
	        System.out.println("ExpectedErrorMessage==> " + emailalredytakenexpectedErrorMsg);
	        driver.close();
	        Assert.assertEquals(emailalredytakenexpectedErrorMsg, emailalredytakenactualErrorMsg);
		}
	

	// Verify signup page with Contact Number is blank
	@Test(description = "Verify signup page with Contact Number is blank")
	public void verifyContactNumberIsBlank() {
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, EMPTY_STR, PASSWORD, CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		String contactErrorMessageExpected = "Please enter contact number!";
		WebElement contactnumberisblank = driver.findElement(By.id("contactNumber.errors"));
		String contactErrorMessageActual = contactnumberisblank.getText();
		System.out.println("ExpectedErrorMessage==> " + contactErrorMessageExpected);
		driver.close();
		Assert.assertEquals(contactErrorMessageExpected, contactErrorMessageActual);

	}

	// Verify signup page with Password is blank
	@Test(description = "Verify signup page with Password is blank")
	public void verifyPasswordIsBlank() {
		WebDriver driver = commonHandler.homePage();

		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, EMPTY_STR,CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		String passwordErrorMessageExpected = "Please enter password!";
		WebElement passwordErrorElement = driver.findElement(By.id("password.errors"));
		String passwordErrorMessageActual = passwordErrorElement.getText();
		System.out.println("ExpectedErrorMessage==> " + passwordErrorMessageExpected);
		driver.close();

		Assert.assertEquals(passwordErrorMessageActual, passwordErrorMessageExpected);
	}

	// Verify signup page with Confirm Password is Not Match
	@Test(description = "Verify signup page with Confirm Password is Not Match")
	public void verifyConfirmPasswordIsNotMatch() {
		WebDriver driver = commonHandler.homePage();
		
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD, EMPTY_STR);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		String confirmpasswordErrorMessageExpected = "Password does not match confirm password!";
		WebElement confirmpasswordnotmatch = driver.findElement(By.id("confirmPassword.errors"));
		String confirmpasswordErrorMessageActual = confirmpasswordnotmatch.getText();
		System.out.println("ExpectedErrorMessage==> " + confirmpasswordErrorMessageExpected);
		driver.close();
		Assert.assertEquals(confirmpasswordErrorMessageExpected, confirmpasswordErrorMessageActual);
	}

	// Verify signup page with Role is selected as User
	@Test(description = "Verify signup page with User Role is selected")
	public void verifyUserRoleIsSelected() {
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);

		String user = driver.findElement(By.id("role1")).getAttribute("checked");
		Assert.assertTrue(Boolean.valueOf(user));
		
		driver.close();
		
		
	}

	// Verify Sign Up - Address page with valid details
	@Test(description = "Verify Sign Up - Address page with valid details")
	public void verifySignUpAddressPage() throws SQLException {
		//Delete data from database
		databaseOperations.cleanUserDetail(NEW_EMAIL);
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME,NEW_EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);

		driver.close();
	}

	// Verify Sign Up - Address page with address line one is empty
	@Test(description = "Verify Sign Up - Address page with address line one is empty")
	public void verifyEmptyAddressLineOne() throws SQLException {
		databaseOperations.cleanUserDetail(NEW_EMAIL);
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
		signUpHandler.setAddressDetails(driver, EMPTY_STR, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

		String addressoneErrorMessageExpected = "Please enter address line one!";
		WebElement addressoneisblank = driver.findElement(By.id("addressLineOne.errors"));
		String addressoneErrorMessageActual = addressoneisblank.getText();
		System.out.println("ExpectedErrorMessage==> " + addressoneErrorMessageExpected);
		driver.close();
		Assert.assertEquals(addressoneErrorMessageExpected, addressoneErrorMessageActual);

	}

	// Verify Sign Up - Address page with address line two is empty
	@Test(description = "Verify Sign Up - Address page with address line two is empty")
	public void verifyEmptyAddressLineTwo() throws SQLException {
		databaseOperations.cleanUserDetail(NEW_EMAIL);
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
		signUpHandler.setAddressDetails(driver, ADDRESS_ONE, EMPTY_STR, CITY, POSTAL_CODE, STATE, COUNTRY);
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

		String addresstwoErrorMessageExpected = "Please enter address line two!";
		WebElement addresstwoisblank = driver.findElement(By.id("addressLineTwo.errors"));
		String addresstwoErrorMessageActual = addresstwoisblank.getText();
		System.out.println("ExpectedErrorMessage==> " + addresstwoErrorMessageExpected);
		driver.close();
		Assert.assertEquals(addresstwoErrorMessageExpected, addresstwoErrorMessageActual);

	}

	// Verify Sign Up - Address page with city is empty
	@Test(description = "Verify Sign Up - Address page with city is empty")
	public void verifyEmptyCity() throws SQLException {
		WebDriver driver = commonHandler.homePage();
		databaseOperations.cleanUserDetail(NEW_EMAIL);
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
		signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, EMPTY_STR, POSTAL_CODE, STATE, COUNTRY);
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

		String cityErrorMessageExpected = "Please enter City!";
		WebElement cityisblank = driver.findElement(By.id("city.errors"));
		String cityErrorMessageActual = cityisblank.getText();
		System.out.println("ExpectedErrorMessage==> " + cityErrorMessageExpected);
		driver.close();
		Assert.assertEquals(cityErrorMessageExpected, cityErrorMessageActual);
	}

	// Verify Sign Up - Address page with postal code is empty
	@Test(description = "Verify Sign Up - Address page with postal code is empty")
	public void verifyEmptyPostalCode() throws SQLException {
		databaseOperations.cleanUserDetail(NEW_EMAIL);
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
		signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, EMPTY_STR, STATE, COUNTRY);
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

		String postalcodeErrorMessageExpected = "Please enter Postal Code!";
		WebElement postalcodeisblank = driver.findElement(By.id("postalCode.errors"));
		String postalcodeErrorMessageActual = postalcodeisblank.getText();
		System.out.println("ExpectedErrorMessage==> " + postalcodeErrorMessageExpected);
		driver.close();
		Assert.assertEquals(postalcodeErrorMessageExpected, postalcodeErrorMessageActual);

	}

	// Verify Sign Up - Address page with state is empty
	@Test(description = "Verify Sign Up - Address page with state is empty")
	public void verifyEmptystate() throws SQLException {
		databaseOperations.cleanUserDetail(NEW_EMAIL);
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
		signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, EMPTY_STR, COUNTRY);
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

		String stateErrorMessageExpected = "Please enter State!";
		WebElement stateisblank = driver.findElement(By.id("state.errors"));
		String stateErrorMessageActual = stateisblank.getText();
		System.out.println("ExpectedErrorMessage==> " + stateErrorMessageExpected);
		driver.close();
		Assert.assertEquals(stateErrorMessageExpected, stateErrorMessageActual);
	}

	// Verify Sign Up - Address page with country is empty
	@Test(description = "Verify Sign Up - Address page with country is empty")
	public void verifyEmptycountry() throws SQLException {
		databaseOperations.cleanUserDetail(NEW_EMAIL);
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
		signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, EMPTY_STR);
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

		String countryErrorMessageExpected = "Please enter country!";
		WebElement countryisblank = driver.findElement(By.id("country.errors"));
		String countryErrorMessageActual = countryisblank.getText();
		System.out.println("ExpectedErrorMessage==> " + countryErrorMessageExpected);
		driver.close();
		Assert.assertEquals(countryErrorMessageExpected, countryErrorMessageActual);

	}

	@Test(description = "Verify personal details clicking on Back-Personal button")
	public void verifyBackButtonPersonalDetails() {
		WebDriver driver = commonHandler.homePage();
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

		driver.close();

	}

	@Test(description = "Verify personal details clicking on Back-Personal button")
	public void verifyBackButtonPersonalDetails1() throws SQLException {
		databaseOperations.cleanUserDetail(NEW_EMAIL);
		// open home page
		WebDriver driver = commonHandler.homePage();

		// find and click sign up button
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);

		// enter valid personal details
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);

		// find and submit next button
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		// enter valid address details
		signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);

		// find and submit back button
		signUpHandler.clickButton(driver, LABEL_BACK_BUTTON);

		// find and submit next button
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		// find and submit confirm address button
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);
		
		driver.close();

	}

	@Test(description = "Verify Edit button on Personal Details")
	public void verifyEditButtonOnPersonalDetails()throws SQLException {
		databaseOperations.cleanUserDetail(EMAIL);
		
		// open home page
		WebDriver driver = commonHandler.homePage();

		// find and click sign up button
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);

		// enter valid personal details
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);

		// find and submit next button
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		// enter valid address details
		signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);

		// find and submit confirm address button
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);
		WebElement button = driver.findElements(By.tagName("a")).get(1);
		button.click();
		//Actions actions = new Actions(driver);
		//actions.moveToElement(button).click().build().perform();
		// find and submit next button
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
		// find and submit confirm address button
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);
		driver.findElements(By.tagName("a")).get(3).click();
		
		String WelcomeMessageExpected = "Welcome!";
		WebElement WelcomeMessageElement = driver.findElement(By.tagName("h1"));
		String WelcomeMessageActual = WelcomeMessageElement.getText();

		Assert.assertEquals(WelcomeMessageExpected, WelcomeMessageActual);
		driver.findElement(By.xpath("//a[@href='/medicare/login']")).click();
		driver.close(); 
		
	}

	@Test(description = "Verify Edit button on Billing Address")
	public void verifyEditButtonOnBillingAddress() throws SQLException {
		databaseOperations.cleanUserDetail(EMAIL);
		// open home page
		WebDriver driver = commonHandler.homePage();

		// find and click sign up button
		signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);

		// enter valid personal details
		signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD,
				CONFIRM_PASSWORD);

		// find and submit next button
		signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

		// enter valid address details
		signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);

		// find and submit confirm address button
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);
		driver.findElements(By.tagName("a")).get(2).click();
		// find and submit confirm address button
		signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);
		// find and submit confirm address button
		//signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);
		driver.findElements(By.tagName("a")).get(3).click();
		
		String WelcomeMessageExpected = "Welcome!";
		WebElement WelcomeMessageElement = driver.findElement(By.tagName("h1"));
		String WelcomeMessageActual = WelcomeMessageElement.getText();
		System.out.println("ExpectedErrorMessage==> " + WelcomeMessageExpected);
		Assert.assertEquals(WelcomeMessageExpected, WelcomeMessageActual);
		driver.findElement(By.xpath("//a[@href='/medicare/login']")).click();
		driver.close();
		

	}
}
