package org.hquijano.tests.ReqResTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class ReqResTest {

    String baseURL = "https://reqres.in";
    RequestSpecification reqSpec;
    String userID;

    @BeforeSuite
    public void setup(){
        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBasePath("/api")
                .setBaseUri(baseURL)
                .addHeader("x-api-key", "reqres-free-v1")
                .build();
    }

    @Test(enabled = false)
    public void testGetListOfUsersAtPage2() {
        given().spec(reqSpec);
        String response = given().spec(reqSpec)
                .queryParam("page", 2)
                .when().get("/users")
                .then().statusCode(200).extract().response().asPrettyString();

        JsonPath jsonPath = new JsonPath(response);
        Assert.assertEquals(jsonPath.getString("data[0].first_name"), "Michael");
        Assert.assertEquals(jsonPath.getString("data[0].email"), "michael.lawson@reqres.in");
        Assert.assertEquals(jsonPath.getInt("data[0].id"), 7);
    }

    @Test(dependsOnMethods = "testCreateUser", enabled = false)
    public void testGetSingleUserById() {
        given().spec(reqSpec);
        String response = given().spec(reqSpec)
                .when().get("/users/" + userID)
                .then().log().all().extract().response().asPrettyString();

        System.out.println(response);
    }

    @Test(dataProvider = "userData", enabled = false)
    public void testCreateUser(String userName, String jobTitle){
        Map<String, String> body = new HashMap<>();
        body.put("name", userName);
        body.put("job", jobTitle);

        given().spec(reqSpec);

        String response = given().spec(reqSpec)
                .body(body).log().all()
                .when().post("/users")
                .then().statusCode(201).extract().response().asPrettyString();

        System.out.println(response);

        userID = new JsonPath(response).getString("id");
    }

    @DataProvider(name = "userData")
    public Object [][] userData(){
        return new Object[][]{
                {"HQuijano", "Senior QA"},
                {"LCetina", "Project Manager"},
                {"MQuijano", "Developer"},
                {"TQuijano", "Junior QA"}
        };
    }

}
