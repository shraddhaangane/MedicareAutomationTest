package com.medicare.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpHandler {


    public void setPersonalDetails(WebDriver driver, String firstName, String lastName, String email, String contactNumber, String password, String confirmPassword) {

        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("email")).sendKeys(email);
        driver.findElement(By.id("contactNumber")).sendKeys(contactNumber);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("confirmPassword")).sendKeys(confirmPassword);
    }

    public void setAddressDetails(WebDriver driver, String addressOne, String addressTwo, String city, String postalCode, String state, String contry) {

        driver.findElement(By.id("addressLineOne")).sendKeys(addressOne);
        driver.findElement(By.id("addressLineTwo")).sendKeys(addressTwo);
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("postalCode")).sendKeys(postalCode);
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("country")).sendKeys(contry);
    }

    public void clickButton(WebDriver driver, String buttonLabel) {
        driver.findElement(By.name(buttonLabel)).click();
    }

    public void clickButtonUsingLinkText(WebDriver driver, String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }
}

