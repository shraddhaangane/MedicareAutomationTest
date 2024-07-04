package com.medicare.addtocart;



import static com.medicare.helper.Constants.*;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.medicare.handlers.CommonHandler;
import com.medicare.handlers.LoginHandler;
import com.medicare.handlers.PaymentHandler;

public class AddToCartTests {
	CommonHandler commonHandler = new CommonHandler();
	LoginHandler loginHandler = new LoginHandler();
	PaymentHandler paymentHandler = new PaymentHandler();
	
	//Verify user is able to view all product list
	@Test(description ="Verify user is able to view product list")
	public void verifyUserAbleToViewtProduct() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, EMAIL, PASSWORD);
		driver.findElement(By.className("navbar-brand")).click();
		driver.close();
		
	}
	
	//Verify user is able to select product and add into cart
	@Test(description ="Verify user is able to select product and add into cart")
	public void verifyUserToSelectandAddToCartProduct() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, EMAIL, PASSWORD);
		driver.findElement(By.linkText("View Products")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/medicare/show/1/product')]")).click();
		driver.findElement(By.xpath("//a[contains(@href,'/medicare/cart/add/1/product')]")).click();
	    driver.close();
	}
	
	//Validate One item is added to the cart and checkout is working
	@Test(description = "Validate One item is added to the cart and checkout is working")
	
	public void validateOneItemAddToCartAndCheckout() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, EMAIL, PASSWORD);
		driver.findElement(By.linkText("View Products")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/medicare/show/1/product')]")).click();
		driver.findElement(By.xpath("//a[contains(@href,'/medicare/cart/add/1/product')]")).click();
		driver.findElement(By.xpath("//a[contains(@href,'/medicare/cart/validate')]")).click();
		driver.findElement(By.linkText("Select")).click();
		paymentHandler.setPaymentDetails(driver,CARD_NUMBER,MONTH, YEAR, CVCODE);
		
		String orderConfirmMsgExpected = "Your Order is Confirmed!!";
		WebElement orderconfirmElement = driver.findElement(By.cssSelector("h3.text-center"));
		String orderConfirmMsgActual = orderconfirmElement.getText();
		driver.close();
		Assert.assertEquals(orderConfirmMsgExpected, orderConfirmMsgActual);
	
	}
	
	//Validate Two Items added to the cart and checkout is working
     @Test(description = "Validate Two item is added to the cart and checkout is working")
	
	public void validateTwoItemAddToCartAndCheckout() {
		WebDriver driver = commonHandler.homePage();

		driver.findElement(By.linkText("Login")).click();
		loginHandler.setloginPage(driver, EMAIL, PASSWORD);
		driver.findElement(By.linkText("View Products")).click();
		driver.findElement(By.xpath("//a[contains(@href,'/medicare/cart/add/1/product')]")).click();
		driver.findElement(By.xpath("//a[contains(@href,'/medicare/show/all/products')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//a[contains(@href,'/medicare/cart/add/2/product')]")).click();
		driver.findElement(By.xpath("//a[contains(@href,'/medicare/cart/validate')]")).click();
		driver.findElement(By.linkText("Select")).click();
		paymentHandler.setPaymentDetails(driver,CARD_NUMBER,MONTH, YEAR, CVCODE);
		
		String orderConfirmMsgExpected = "Your Order is Confirmed!!";
		WebElement orderconfirmElement = driver.findElement(By.cssSelector("h3.text-center"));
		String orderConfirmMsgActual = orderconfirmElement.getText();
		//driver.close();
		Assert.assertEquals(orderConfirmMsgExpected, orderConfirmMsgActual);
		
	}
	

}
