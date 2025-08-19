package org.hquijano.tests.practice;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.notNullValue;

public class WeatherAPI {
    String api_key = System.getProperty("api_key");

    @Test
    public void WeatherAPI(){
        RestAssured.baseURI = "http://api.weatherapi.com/v1";
        given().log().all()
                .queryParam("key", api_key)
                .queryParam("q", "Madrid, Spain")
                .queryParam("days", 3)
                .queryParam("aqi", "no")
                .when().get("/current.json")
                .then()
                .statusCode(200)
                .body("current.temp_c", notNullValue())  // Validate that temperature is returned
                .log().body();                           // Show response

        String location = given().log().all()
                .queryParam("key", api_key)
                .queryParam("q", "Madrid, Spain")
                .when().get("/current.json")
                .then()
                .statusCode(200)
                .extract().path("location.name");

        System.out.println(location);
    }


}
