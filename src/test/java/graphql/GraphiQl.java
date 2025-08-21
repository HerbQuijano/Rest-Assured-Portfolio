package graphql;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GraphiQl {

    @Test
    public void testCreateLocationGraphQl() {
        String baseURL = "https://rahulshettyacademy.com/gq/graphql";

        String mutation = """
                mutation($location: LocationCreate)
                {
                    createLocation(location: $location)
                        {
                            id
                        }
                }
                """.replace("\n", " ");

        Map<String, Object> location = new HashMap<>();
        location.put("name", "Campeche");
        location.put("type", "City");
        location.put("dimension", "2254");

        Map<String, Object> variables = new HashMap<>();
        variables.put("location", location);

        Map<String, Object> body = new HashMap<>();
        body.put("query", mutation);
        body.put("variables", variables);

        Response response = given().log().all()
                .baseUri(baseURL)
                .contentType(ContentType.JSON)
                .body(body)
                .when().post()
                .then().log().all()
                .statusCode(200)
                .extract().response();

        System.out.println(response.asPrettyString());
    }
}
