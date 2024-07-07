package com.medicare.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentHandler {
    public void setPaymentDetails(WebDriver driver, String cardnumber, String month, String year, String cvcode) {

        driver.findElement(By.id("cardNumber")).sendKeys(cardnumber);
        driver.findElement(By.id("expityMonth")).sendKeys(month);
        driver.findElement(By.id("expityYear")).sendKeys(year);
        driver.findElement(By.id("cvCode")).sendKeys(cvcode);

        driver.findElement(By.linkText("Pay")).click();
    }
}
