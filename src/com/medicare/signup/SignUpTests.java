package com.medicare.signup;

import static com.medicare.helper.Constants.*;

import java.sql.SQLException;
import java.time.Duration;

import com.medicare.config.BaseConfigurationTests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.medicare.database.DatabaseOperations;
import com.medicare.handlers.SignUpHandler;

public class SignUpTests extends BaseConfigurationTests {
    SignUpHandler signUpHandler = new SignUpHandler();
    DatabaseOperations databaseOperations = new DatabaseOperations();

    // Verify signup page with Empty User Details
    @Test(description = "Signup page : empty user details")
    public void verifySignUpPageWithEmptyUserDetails() {
        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, EMPTY_STR, EMPTY_STR, EMPTY_STR, EMPTY_STR, EMPTY_STR, EMPTY_STR);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
    }

    // Verify signup page with First Name is blank
    @Test(description = "Signup page : blank first Name")
    public void verifyFirstNameIsBlank() {
        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, EMPTY_STR, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        String firstNameErrorMessageExpected = "Please enter first name!";
        WebElement firstNameBlank = driver.findElement(By.id("firstName.errors"));
        String firstNameErrorMessageActual = firstNameBlank.getText();
        System.out.println("ExpectedErrorMessage==> " + firstNameErrorMessageExpected);

        Assert.assertEquals(firstNameErrorMessageExpected, firstNameErrorMessageActual);
    }

    // Verify signup page with Last Name is blank
    @Test(description = "Signup page : blank last name")
    public void verifyLastNameIsBlank() {
        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, EMPTY_STR, EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        String lastNameErrorMessageExpected = "Please enter last name!";
        WebElement lastNameisblank = driver.findElement(By.id("lastName.errors"));
        String lastNameErrorMessageActual = lastNameisblank.getText();
        System.out.println("ExpectedErrorMessage==> " + lastNameErrorMessageExpected);

        Assert.assertEquals(lastNameErrorMessageExpected, lastNameErrorMessageActual);
    }

    // Verify signup page with Email is blank
    @Test(description = "Signup page : blank email")
    public void verifyEmailIsBlank() {
        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMPTY_STR, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        String emailErrorMessageExpected = "Please enter email address!";
        WebElement emailisBlank = driver.findElement(By.id("email.errors"));
        String emailErrorMessageActual = emailisBlank.getText();
        System.out.println("ExpectedErrorMessage==> " + emailErrorMessageExpected);

        Assert.assertEquals(emailErrorMessageExpected, emailErrorMessageActual);
    }

    // Verify signup page with Contact Number is blank
    @Test(description = "Signup page : blank contact number")
    public void verifyContactNumberIsBlank() {
        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, EMPTY_STR, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        String contactErrorMessageExpected = "Please enter contact number!";
        WebElement contactnumberisblank = driver.findElement(By.id("contactNumber.errors"));
        String contactErrorMessageActual = contactnumberisblank.getText();
        System.out.println("ExpectedErrorMessage==> " + contactErrorMessageExpected);

        Assert.assertEquals(contactErrorMessageExpected, contactErrorMessageActual);
    }

    // Verify signup page with Password is blank
    @Test(description = "Signup page : blank Password")
    public void verifyPasswordIsBlank() {
        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, EMPTY_STR, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        String passwordErrorMessageExpected = "Please enter password!";
        WebElement passwordErrorElement = driver.findElement(By.id("password.errors"));
        String passwordErrorMessageActual = passwordErrorElement.getText();
        System.out.println("ExpectedErrorMessage==> " + passwordErrorMessageExpected);

        Assert.assertEquals(passwordErrorMessageActual, passwordErrorMessageExpected);
    }

    // Verify signup page with Confirm Password is Not Match
    @Test(description = "Signup page : password mismatch")
    public void verifyConfirmPasswordIsNotMatch() {

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD, EMPTY_STR);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        String confirmpasswordErrorMessageExpected = "Password does not match confirm password!";
        WebElement confirmpasswordnotmatch = driver.findElement(By.id("confirmPassword.errors"));
        String confirmpasswordErrorMessageActual = confirmpasswordnotmatch.getText();
        System.out.println("ExpectedErrorMessage==> " + confirmpasswordErrorMessageExpected);

        Assert.assertEquals(confirmpasswordErrorMessageExpected, confirmpasswordErrorMessageActual);
    }

    // Verify signup page with Role is selected as User
    @Test(description = "Signup page : selected user role")
    public void verifyUserRoleIsSelected() {
        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);

        String user = driver.findElement(By.id("role1")).getAttribute("checked");
        Assert.assertTrue(Boolean.parseBoolean(user));
    }

    // priority 1 test cases

    // Verify signup page with valid user details
    @Test(description = "Signup page : valid user details", priority = 1)
    public void verifySignUpPageWithValidUserDetails() throws SQLException {
        databaseOperations.cleanUserDetails(NEW_EMAIL);

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        //TODO Add assert statements
    }

    // Verify Sign Up - Address page with address line one is empty
    @Test(description = "Sign Up - Empty address line one", priority = 1)
    public void verifyEmptyAddressLineOne() throws SQLException {
        databaseOperations.cleanUserDetails(NEW_EMAIL);

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
        signUpHandler.setAddressDetails(driver, EMPTY_STR, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);
        signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

        String addressoneErrorMessageExpected = "Please enter address line one!";
        WebElement addressoneisblank = driver.findElement(By.id("addressLineOne.errors"));
        String addressoneErrorMessageActual = addressoneisblank.getText();
        System.out.println("ExpectedErrorMessage==> " + addressoneErrorMessageExpected);

        Assert.assertEquals(addressoneErrorMessageExpected, addressoneErrorMessageActual);
    }

    // Verify Sign Up - Address page with address line two is empty
    @Test(description = "Signup page - empty address line two", priority = 1)
    public void verifyEmptyAddressLineTwo() throws SQLException {
        databaseOperations.cleanUserDetails(NEW_EMAIL);

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
        signUpHandler.setAddressDetails(driver, ADDRESS_ONE, EMPTY_STR, CITY, POSTAL_CODE, STATE, COUNTRY);
        signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

        String addresstwoErrorMessageExpected = "Please enter address line two!";
        WebElement addresstwoisblank = driver.findElement(By.id("addressLineTwo.errors"));
        String addresstwoErrorMessageActual = addresstwoisblank.getText();
        System.out.println("ExpectedErrorMessage==> " + addresstwoErrorMessageExpected);

        Assert.assertEquals(addresstwoErrorMessageExpected, addresstwoErrorMessageActual);
    }

    // Verify Sign Up - Address page with city is empty
    @Test(description = "Signup page - empty city", priority = 1)
    public void verifyEmptyCity() throws SQLException {
        databaseOperations.cleanUserDetails(NEW_EMAIL);

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
        signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, EMPTY_STR, POSTAL_CODE, STATE, COUNTRY);
        signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

        String cityErrorMessageExpected = "Please enter City!";
        WebElement cityisblank = driver.findElement(By.id("city.errors"));
        String cityErrorMessageActual = cityisblank.getText();
        System.out.println("ExpectedErrorMessage==> " + cityErrorMessageExpected);

        Assert.assertEquals(cityErrorMessageExpected, cityErrorMessageActual);
    }

    // Verify Sign Up - Address page with postal code is empty
    @Test(description = "Signup page - empty postal code", priority = 1)
    public void verifyEmptyPostalCode() throws SQLException {
        databaseOperations.cleanUserDetails(NEW_EMAIL);

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
        signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, EMPTY_STR, STATE, COUNTRY);
        signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

        String postalcodeErrorMessageExpected = "Please enter Postal Code!";
        WebElement postalcodeisblank = driver.findElement(By.id("postalCode.errors"));
        String postalcodeErrorMessageActual = postalcodeisblank.getText();
        System.out.println("ExpectedErrorMessage==> " + postalcodeErrorMessageExpected);

        Assert.assertEquals(postalcodeErrorMessageExpected, postalcodeErrorMessageActual);
    }

    // Verify Sign Up - Address page with state is empty
    @Test(description = "Signup page - empty state", priority = 1)
    public void verifyEmptystate() throws SQLException {
        databaseOperations.cleanUserDetails(NEW_EMAIL);

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
        signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, EMPTY_STR, COUNTRY);
        signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

        String stateErrorMessageExpected = "Please enter State!";
        WebElement stateisblank = driver.findElement(By.id("state.errors"));
        String stateErrorMessageActual = stateisblank.getText();
        System.out.println("ExpectedErrorMessage==> " + stateErrorMessageExpected);

        Assert.assertEquals(stateErrorMessageExpected, stateErrorMessageActual);
    }

    // Verify Sign Up - Address page with country is empty
    @Test(description = "Signup page - empty country", priority = 1)
    public void verifyEmptycountry() throws SQLException {
        databaseOperations.cleanUserDetails(NEW_EMAIL);

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
        signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, EMPTY_STR);
        signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

        String countryErrorMessageExpected = "Please enter country!";
        WebElement countryisblank = driver.findElement(By.id("country.errors"));
        String countryErrorMessageActual = countryisblank.getText();
        System.out.println("ExpectedErrorMessage==> " + countryErrorMessageExpected);

        Assert.assertEquals(countryErrorMessageExpected, countryErrorMessageActual);
    }

    // priority 2 tests

    // Verify Sign Up - Address page with valid details
    @Test(description = "Signup page - valid address details", priority = 2)
    public void verifySignUpAddressPage() throws SQLException {
        //Delete data from database
        databaseOperations.cleanUserDetails(NEW_EMAIL);

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);
        signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);
        driver.findElement(By.xpath("//a[contains(text(),'Confirm')]")).click();

        //TODO add asserts here
    }

    //here
    // Verify signup page with Email address is already taken
    @Test(description = "Signup page : email address is already exist", priority = 2)
    public void verifyEmailIsAlreadyTaken() throws SQLException {
        databaseOperations.cleanUserDetails(EMAIL);

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);
        signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);
        driver.findElement(By.xpath("//a[contains(text(),'Confirm')]")).click();

        driver.get(HOME_PAGE_URL);

        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);
        String emailAlredyTakenExpectedErrorMsg = "Email address is already taken!";

        WebElement emailAlreadyTakenMsg = driver.findElement(By.id("email.errors"));
        String emailAlredyTakenActualErrorMsg = emailAlreadyTakenMsg.getText();
        System.out.println("ExpectedErrorMessage==> " + emailAlredyTakenExpectedErrorMsg);

        Assert.assertEquals(emailAlredyTakenExpectedErrorMsg, emailAlredyTakenActualErrorMsg);
    }

    @Test(description = "Verify personal details clicking on Back-Personal button", priority = 2)
    public void verifyBackButtonPersonalDetails1() throws SQLException {
        databaseOperations.cleanUserDetails(NEW_EMAIL);

        // find and click sign up button
        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);

        // enter valid personal details
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, NEW_EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);

        // find and submit next button
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        // enter valid address details
        signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);

        // find and submit back button
        signUpHandler.clickButton(driver, LABEL_BACK_BUTTON);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("scroll(250, 0)");
        // find and submit next button
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        // find and submit confirm address button
        signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);

        // TODO add assert statements here
    }

    // priority 3 tests
    //here
    @Test(description = "Verify Edit button on Personal Details", priority = 3)
    public void verifyEditButtonOnPersonalDetails() throws SQLException {
        databaseOperations.cleanUserDetails(EMAIL);

        // find and click sign up button
        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);

        // enter valid personal details
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);

        // find and submit next button
        signUpHandler.clickButton(driver, LABEL_NEXT_BUTTON);

        // enter valid address details
        signUpHandler.setAddressDetails(driver, ADDRESS_ONE, ADDRESS_TWO, CITY, POSTAL_CODE, STATE, COUNTRY);

        // find and submit confirm address button
        signUpHandler.clickButton(driver, LABEL_CONFIRM_BUTTON);
        WebElement button = driver.findElements(By.tagName("a")).get(1);
        button.click();

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
    }

    @Test(description = "Verify Edit button on Billing Address", priority = 3)
    public void verifyEditButtonOnBillingAddress() throws SQLException {
        databaseOperations.cleanUserDetails(EMAIL);

        // find and click sign up button
        signUpHandler.clickButtonUsingLinkText(driver, LINK_TEXT_SIGNUP_BUTTON);

        // enter valid personal details
        signUpHandler.setPersonalDetails(driver, FIRST_NAME, LAST_NAME, EMAIL, CONTACT_NUMBER, PASSWORD, CONFIRM_PASSWORD);

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
    }
}