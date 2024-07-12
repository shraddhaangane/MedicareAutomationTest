package com.medicare.config;

import com.medicare.handlers.CommonHandler;
import groovy.util.logging.Slf4j;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

@Slf4j
public class BaseConfigurationTests {

    private static final Logger log = LoggerFactory.getLogger(BaseConfigurationTests.class);
    private final CommonHandler commonHandler = new CommonHandler();
    public WebDriver driver;

    @BeforeMethod
    public void setup(){
        driver = commonHandler.homePage();
        log.info("WebDriver Initialized");
    }

    @AfterMethod
    public void destroy(){
        driver.close();
        log.info("WebDriver Destroyed");
    }
}
