package org.hquijano.tests.APIIntro;

import io.restassured.path.json.JsonPath;
import org.hquijano.tests.practice.WireMockDemo;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class GetCoursesTest {
    JsonPath jsResponse;
    SoftAssert softAssert = new SoftAssert();

    @BeforeSuite
    public void setup() {
        WireMockDemo.setup();
        String baseURI = "http://localhost:8080";

        String response = given()//.log().all()
                .when().get("/getCourses")
                .then()//.log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().asString();

        jsResponse = new JsonPath(response);
    }

    @AfterSuite
    public void teardown() {
        WireMockDemo.teardown();
    }

    @Test
    public void testNumberOfCourses() {
        int numberOfCourses = jsResponse.getInt("courses.size()");
        Assert.assertEquals(numberOfCourses, 3, "Number of courses does not match");
    }

    @Test
    public void testPurchaseAmount() {
        Double purchaseAmount = jsResponse.getDouble("dashboard.purchaseAmount");
        Assert.assertEquals(purchaseAmount, 910);
    }

    @Test
    public void testTitleOfFirstCourse() {
        String firsBookTitle = jsResponse.getString("courses[0].title");
        Assert.assertEquals(firsBookTitle, "Selenium Python");
    }

    @Test
    public void testPrintAllCoursesAndPrices() {
        for (int i = 0; i < jsResponse.getInt("courses.size()"); i++){
            String course = jsResponse.getString("courses["+ i + "].title");
            Double price = jsResponse.getDouble("courses["+ i + "].price");

            System.out.println(course + " $" + price);
        }
    }

    @Test
    public void testPrintNumberOfCopiesSoldbyRPA() {
        int copiesSold = jsResponse.getInt("courses.find { it.title == 'RPA' }.copies");
        Assert.assertEquals(copiesSold, 10);
    }

    @Test
    public void testSumCourses(){
        Double purchaseAmount = jsResponse.getDouble("dashboard.purchaseAmount");
        Double coursesAmount = jsResponse.getDouble("courses.sum { it.price }");
        softAssert.assertEquals(purchaseAmount, coursesAmount, "amounts do not match");
        softAssert.assertAll();
    }
}


