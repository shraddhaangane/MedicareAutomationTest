package com.medicare.addtocart;


import static com.medicare.helper.Constants.*;

import java.time.Duration;

import com.medicare.config.BaseConfigurationTests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.medicare.handlers.CommonHandler;
import com.medicare.handlers.LoginHandler;
import com.medicare.handlers.PaymentHandler;

public class AddToCartTests extends BaseConfigurationTests {
    CommonHandler commonHandler = new CommonHandler();
    LoginHandler loginHandler = new LoginHandler();
    PaymentHandler paymentHandler = new PaymentHandler();

    //Verify user is able to view all product list
    @Test(description = "Verify user is able to view product list")
    public void verifyUserAbleToViewtProduct() {
        driver.findElement(By.linkText(LINK_LOGIN_TEXT)).click();
        loginHandler.setloginPage(driver, EMAIL, PASSWORD);
        driver.findElement(By.className("navbar-brand")).click();
    }

    //here
    //Verify user is able to select product and add into cart
    @Test(description = "Verify user is able to select product and add into cart")
    public void verifyUserToSelectandAddToCartProduct() {
        driver.findElement(By.linkText(LINK_LOGIN_TEXT)).click();
        loginHandler.setloginPage(driver, EMAIL, PASSWORD);
        driver.findElement(By.linkText(LINK_VIEW_PRODUCTS_TEXT)).click();
        driver.findElement(By.xpath("//a[contains(@href, '/medicare/show/1/product')]")).click();
        driver.findElement(By.xpath(XPATH_PRODUCT_ONE)).click();
    }

    //here
    //Validate One item is added to the cart and checkout is working
    @Test(description = "Validate One item is added to the cart and checkout is working")
    public void validateOneItemAddToCartAndCheckout() {
        driver.findElement(By.linkText(LINK_LOGIN_TEXT)).click();
        loginHandler.setloginPage(driver, EMAIL, PASSWORD);
        driver.findElement(By.linkText(LINK_VIEW_PRODUCTS_TEXT)).click();
        driver.findElement(By.xpath("//a[contains(@href, '/medicare/show/1/product')]")).click();
        driver.findElement(By.xpath(XPATH_PRODUCT_ONE)).click();
        driver.findElement(By.xpath("//a[contains(@href,'/medicare/cart/validate')]")).click();
        driver.findElement(By.linkText("Select")).click();
        paymentHandler.setPaymentDetails(driver, CARD_NUMBER, MONTH, YEAR, CVCODE);

        String orderConfirmMsgExpected = "Your Order is Confirmed!!";
        WebElement orderconfirmElement = driver.findElement(By.cssSelector("h3.text-center"));
        String orderConfirmMsgActual = orderconfirmElement.getText();
        Assert.assertEquals(orderConfirmMsgExpected, orderConfirmMsgActual);

    }

    //here
    //Validate Two Items added to the cart and checkout is working
    @Test(description = "Validate Two item is added to the cart and checkout is working")
    public void validateTwoItemAddToCartAndCheckout() {
        driver.findElement(By.linkText(LINK_LOGIN_TEXT)).click();
        loginHandler.setloginPage(driver, EMAIL, PASSWORD);
        driver.findElement(By.linkText("View Products")).click();
        driver.findElement(By.xpath("//a[contains(@href,'/medicare/cart/add/1/product')]")).click();
        waitTillElementIsClickable(driver, "//a[contains(@href,'/medicare/show/all/products')]");
        waitTillElementIsClickable(driver, "//a[contains(@href,'/medicare/cart/add/2/product')]");
        waitTillElementIsClickable(driver, "//a[contains(@href,'/medicare/cart/validate')]");
        driver.findElement(By.linkText("Select")).click();
        paymentHandler.setPaymentDetails(driver, CARD_NUMBER, MONTH, YEAR, CVCODE);

        String orderConfirmMsgExpected = "Your Order is Confirmed!!";
        WebElement orderConfirmElement = driver.findElement(By.cssSelector("h3.text-center"));
        String orderConfirmMsgActual = orderConfirmElement.getText();
        Assert.assertEquals(orderConfirmMsgExpected, orderConfirmMsgActual);

    }

    private void waitTillElementIsClickable(WebDriver driver, String expression) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.xpath(expression))).click();
    }
}
