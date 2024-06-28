package com.medicare.handlers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginHandler {

public void setloginPage(WebDriver driver, String email, String password) {
		
		driver.findElement(By.id("username")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		
		driver.findElement(By.xpath("//*[contains(@type,'submit')]")).click();
	}


}


