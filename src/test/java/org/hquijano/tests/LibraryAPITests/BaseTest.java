package org.hquijano.tests.LibraryAPITests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    // Library API methods here
    RequestSpecification reqSpec;

    @BeforeSuite
    public void setup() {
        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBasePath("/library")
                .build();
    }
}
