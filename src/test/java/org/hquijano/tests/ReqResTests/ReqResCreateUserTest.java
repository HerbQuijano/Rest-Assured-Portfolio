package org.hquijano.tests.ReqResTests;

// Importing necessary classes for the test
import org.hquijano.model.CreateUserRequest; // Model class for user creation request
import org.hquijano.model.CreateUserResponse; // Model class for user creation response
import org.testng.Assert; // TestNG assertion library
import org.testng.annotations.Test; // TestNG annotation for test methods

import static io.restassured.RestAssured.given; // Static import for RestAssured's given method

// Test class extending a base class for ReqRes API tests
public class ReqResCreateUserTest extends ReqResBase {
    private final CreateUserRequest request; // Request object for creating a user

    // Constructor to initialize the request object
    public ReqResCreateUserTest(CreateUserRequest request) {
        this.request = request;
    }

    // Test method to create a user
    @Test
    public void createUser() {
        // Sending a POST request to create a user and extracting the response as a CreateUserResponse object
        CreateUserResponse resp =
                given().spec(reqSpec) // Using a predefined request specification
                        .body(request) // Converting the request object to JSON automatically
                        .when().post("/users") // Sending a POST request to the /users endpoint
                        .then().statusCode(201) // Asserting that the response status code is 201 (Created)
                        .extract().as(CreateUserResponse.class); // Converting the JSON response to a CreateUserResponse object

        // Asserting that the response matches the request
        Assert.assertEquals(resp.getName(), request.getName(), "name mismatch"); // Check if the name matches
        Assert.assertEquals(resp.getJob(), request.getJob(), "job mismatch"); // Check if the job matches
        Assert.assertNotNull(resp.getId(), "id should be present"); // Check if the ID is present in the response
        Assert.assertNotNull(resp.getCreatedAt(), "createdAt should be present"); // Check if the createdAt timestamp is present
    }
}