package org.hquijano.tests.JiraAPI_BasicAuth;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class JiraAPIPractice {
    RequestSpecification reqSpec;
    String baseURL = "https://dk-team-practice.atlassian.net/";

    @BeforeSuite
    public void setup(){
        reqSpec = new RequestSpecBuilder()
                .setBaseUri(baseURL)
                .setBasePath("/rest/api/3")
                .addHeader("Authorization","Basic <token>")
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .build();
    }

    @Test
    public void getIssueById(){
        String response = given(reqSpec)
                .pathParams("issueId", "ATP-1")
                .when().get("/issue/{issueId}")
                .then().extract().asPrettyString();

        System.out.println(response);
    }

    @Test
    public void createIssue(){
        String body = "{\n" +
                "          \"fields\": {\n" +
                "            \"project\": { \"key\": \"ATP\" },\n" +
                "            \"summary\": \"Creado via API (RestAssured)\",\n" +
                "            \"issuetype\": { \"name\": \"Error\" }\n" +
                "          }\n" +
                "        }";

        String response = given(reqSpec)
                .body(body)
                .when().post("/issue")
                .then().statusCode(201)
                .extract().asPrettyString();
        System.out.println(response);
    }
}
