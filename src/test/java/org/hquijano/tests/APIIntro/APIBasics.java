package org.hquijano.tests.APIIntro;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import utils.ReusableMethods;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;

public class APIBasics {

    public static void main(String[] args) {
        //given, when, then

        RestAssured.baseURI = "https://rahulshettyacademy.com";
        File file = new File("src/test/resources/AddPlace.json");
        String newAddress = "1600 Amphitheatre Parkway, Mountain View, CA";
        String keyValue = "qaclick123";

        String postResponse = given().log().all()
                .queryParam("key", keyValue)
                .header("Content-Type", "application/json")
                .body(file)
                .when().post("/maps/api/place/add/json")
                .then()
                .assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .extract().response().asPrettyString();

        JsonPath jsonResponse = ReusableMethods.convertStringToJson(postResponse);
        String place_id = jsonResponse.getString("place_id");
        //System.out.println("This is the place_id: " + place_id);

        String getResponse = given().log().all()
                .queryParam("key", keyValue)
                .queryParam("place_id", place_id)
                .when().get("/maps/api/place/get/json")
                .then()//.log().all()
                .assertThat()
                .statusCode(200)
                .body("address", equalTo(""))
                .extract().response().asPrettyString();

        JsonPath getJsonPath = ReusableMethods.convertStringToJson(getResponse);

        String originalAddress = getJsonPath.getString("address");
        //System.out.println("This is the original address: " + originalAddress);

        Map<String, String> updatePlace = new HashMap<>();
        updatePlace.put("place_id", place_id);
        updatePlace.put("address", newAddress);
        updatePlace.put("key", keyValue);


        given().log().all()
                .header("Content-Type", "application/json")
                //.body("{\"place_id\":\"" + place_id + ",\"address\":\"" + newAddress + ",\"key\":\"qaclick123\"}\n")
                //.body("{\"place_id\":\"" + place_id + "\",\"address\":\"" + newAddress + "\",\"key\":\"qaclick123\"}")
                .body(updatePlace)
                .when().put("/maps/api/place/update/json")
                .then().log().all()
                .assertThat()
                .statusCode(200);
        //System.out.println("The new address is: " + newAddress);

        String newGetResponse = given().log().all()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", place_id)
                .when().get("/maps/api/place/get/json")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("address", equalTo(newAddress))
                .extract().response().asPrettyString();

        //System.out.println("This is the new response: " + newResponse);
        //System.out.println(newAddress);
        JsonPath updateJsonResponse = ReusableMethods.convertStringToJson(newGetResponse);
        String updatedAddress = updateJsonResponse.getString("address");
        Assert.assertEquals(updatedAddress, newAddress);
    }
}
