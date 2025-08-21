package OAuthCCPractice;

import com.github.tomakehurst.wiremock.admin.RequestSpec;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.oauth2;

public class ClientCredentials {
    RequestSpecification reqSpec;
    String accessToken;
    String tokenUrl = "https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token";
    String client_id = "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com";
    String client_secret = "erZOWM9g3UtwNRj340YYaK_W";
    String scope = "trust";

    String baseURI = "https://rahulshettyacademy.com";
    String basePath = "/oauthapi";

    @BeforeMethod
    public void setup(){
        Response tokenResponse =
                given()
                        //.auth().preemptive().basic(client_id, client_secret)
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("client_id", client_id)
                        .formParam("client_secret", client_secret)
                        .formParam("grant_type", "client_credentials")
                        .formParam("scope", scope)
                        .when().post(tokenUrl)
                        .then().log().all().assertThat().statusCode(200)
                        .extract().response();

        accessToken = tokenResponse.path("access_token");

        //System.out.println(accessToken);

        reqSpec = new RequestSpecBuilder()
                //.addHeader("Authorization", "Bearer " + accessToken)
                .setBaseUri(baseURI)
                .setBasePath(basePath)
                .build();
    }

    @Test
    public void getCourseDetails(){
        String response = given(reqSpec).log().all()
                //.auth().oauth2(accessToken)
                .queryParam("access_token", accessToken)
                .when().get("getCourseDetails")
                .then().log().all()
                .assertThat().statusCode(401)
                .extract().asPrettyString();

        JsonPath js = new JsonPath(response);

        System.out.println(response);
    }




}
