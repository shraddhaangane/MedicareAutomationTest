package com.medicare.handlers;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;

public class CommonHandler {
	
	public WebDriver homePage() {
	
	  WebDriver driver = new ChromeDriver();
	  driver.get("http://localhost:9090/medicare/");
	  driver.manage().window().maximize();
	  
	  return driver;
    }
 }