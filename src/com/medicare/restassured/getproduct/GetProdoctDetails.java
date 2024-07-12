package com.medicare.restassured.getproduct;

import groovy.util.logging.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

@Slf4j
public class GetProdoctDetails {

    private static final Logger log = LoggerFactory.getLogger(GetProdoctDetails.class);

    @Test
    public void getAllProducts() {
        String response = RestAssured.given().baseUri("http://localhost:8080/medicare/show/all/products")
                .when().get()
                .then().statusCode(200).log().all().toString();

        log.info(response);
    }

}
