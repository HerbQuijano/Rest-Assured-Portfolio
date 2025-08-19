package org.hquijano.tests.Interviews;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import utils.ReusableMethods;

import static io.restassured.RestAssured.*;

public class EmploymentTask {
    public static void main(String[] args) {
        //System.out.println(UsingRequestSpecification());
        System.out.println("---------------------------------");
        //System.out.println(UsingStaticImport());
        System.out.println("---------------------------------");
        //System.out.println(CommonTest());

        //JsonPath jsResponse = new JsonPath(CommonTest());
        JsonPath jsResponse = ReusableMethods.convertStringToJson(CommonTest());
        String title = jsResponse.getString("title");

        System.out.println(title);


    }

    public static String UsingRequestSpecification() {

        RequestSpecification reqSpec = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                .log().all();

        String getResponse = given()
                .spec(reqSpec)
                .when().get("/posts/1")
                .then().statusCode(200)
                        .extract().response().asPrettyString();

        return getResponse;

    }

    public static String UsingStaticImport() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        String getResponse = given()
                .header("Content-Type", "application/json")
                .log().all()
                .when().get("/posts/1")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().asPrettyString();

        return getResponse;
    }

    public static String CommonTest() {
        String getResponse = given()
                .baseUri("https://jsonplaceholder.typicode.com")
                .header("Content-Type", "application/json")
                .log().all()
                .when().get("/posts/1")
                .then().extract().response().asPrettyString();

        return getResponse;
    }
}
