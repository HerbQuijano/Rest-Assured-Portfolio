package org.hquijano;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetCoursesTest {
    JsonPath jsResponse;

    @BeforeSuite
    public void setup() {
        WireMockDemo.setup();
        String baseURI = "http://localhost:8080";

        String response = given().log().all()
                .when().get("/getCourses")
                .then().log().all()
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
        int numberOfCourses = jsResponse.get("courses.size()");
        System.out.println("Number of courses: " + numberOfCourses);

        Assert.assertEquals(numberOfCourses, 3);
    }

    @Test
    public void testNumberOfCoursesList() {
        int courseCount = jsResponse.getList("courses").size();
        Assert.assertEquals(courseCount, 3);
    }


    @Test
    public void testPurchaseAmount() {
        Double purchaseAmount = jsResponse.getDouble("dashboard.purchaseAmount");

        Assert.assertEquals(purchaseAmount, 910.00);
    }

    @Test
    public void testTitleOfFirstCourse() {
        String title = jsResponse.getString("courses[0].title");
        Assert.assertEquals(title, "Selenium Python");
    }

    @Test
    public void testPrintAllCoursesAndPrices() {
        for (int i = 0; i < jsResponse.getList("courses").size(); i++) {
            String title = jsResponse.getString("courses[" + i + "].title");
            Double price = jsResponse.getDouble("courses[" + i + "].price");

            System.out.println("Course: " + title + " Price: " + price);
        }
    }

    @Test
    public void testPrintNumberOfCopiesSold() {
        int copies = jsResponse.getInt("courses.find { it.title == 'RPA' }.copies");
        System.out.println(copies);
    }

    @Test
    public void testSumCourses(){
        Double totalAmount = jsResponse.getDouble("dashboard.purchaseAmount");
        Double amountSum = 0.00;

        for(int i = 0; i < jsResponse.getList("courses").size(); i++){
            amountSum = amountSum + jsResponse.getDouble("courses[" + i + "].price");
        }

        Assert.assertEquals(amountSum, totalAmount);
    }
}


