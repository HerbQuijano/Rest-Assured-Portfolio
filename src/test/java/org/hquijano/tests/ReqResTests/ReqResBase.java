package org.hquijano.tests.ReqResTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class ReqResBase {
    protected static RequestSpecification reqSpec;

    @BeforeSuite
    public void setup(){
        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setAccept(ContentType.JSON)
                .setBaseUri("https://reqres.in")
                .setBasePath("/api")
                .addHeader("x-api-key", "reqres-free-v1")
                .build();
    }
}
