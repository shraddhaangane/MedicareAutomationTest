package com.medicare.restassured.getproduct;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class GetProdoctDetails {

    @Test
    public void getAllProducts() {

        String response = RestAssured.given().baseUri("http://localhost:8080/medicare/show/all/products")

                .when().get()

                .then().statusCode(200).log().all().toString();

        System.out.println(response);
    }

}
