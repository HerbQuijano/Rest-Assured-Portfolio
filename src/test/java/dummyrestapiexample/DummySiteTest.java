package dummyrestapiexample;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DummySiteTest {
    private RequestSpecification reqSpec;
    private String baseURL = "https://dummy.restapiexample.com";
    private String basePath = "/api/v1";

    @BeforeMethod
    public void setup(){
        reqSpec = new RequestSpecBuilder().setBaseUri(baseURL)
                .setBasePath(basePath)
                .setContentType(ContentType.JSON)
                .build();
    }

    @Test
    public void testCreateEmployee(){
        DemoRequest demoRequest = new DemoRequest();
        demoRequest.setName("Sally");
        demoRequest.setSalary(2577);
        demoRequest.setAge(23);

        DemoResponse demoResponse = given(reqSpec)
                .body(demoRequest)
                .when().post("/create")
                .then().log().ifValidationFails()
                .assertThat().statusCode(200)
                .extract().as(DemoResponse.class);

        String status = demoResponse.getStatus();
        String name = demoResponse.getData().getName();

        System.out.println(status + " " + name);
    }

}
