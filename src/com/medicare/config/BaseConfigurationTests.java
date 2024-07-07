package com.medicare.config;

import com.medicare.handlers.CommonHandler;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseConfigurationTests {

    private final CommonHandler commonHandler = new CommonHandler();
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = commonHandler.homePage();
        System.out.println("WebDriver Initialized");
    }

    @AfterMethod
    public void destroy(){
        driver.close();
        System.out.println("WebDriver Destroyed");
    }
}
