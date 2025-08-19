package org.hquijano.tests.practice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

import static io.restassured.RestAssured.given;

public class WeatherAPI2 {
    private static String api_key = System.getProperty("api_key");
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/test/resources/config.properties"));
            api_key = prop.getProperty("api_key");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Enter the city");
        String city = sc.nextLine();

        JsonPath jsonResponse = new JsonPath(forecastCity(city));

        System.out.println("En la ciudad de " + jsonResponse.getString("location.name")
                + " en este momento son las " + jsonResponse.getString("location.localtime")
                + " y actualmente se presentan temperaturas de " + jsonResponse.getDouble("current.temp_c")
                + " el clima se presenta " + jsonResponse.getString("current.condition.text")
                + " con vientos de hasta " + jsonResponse.getString("current.wind_kph") + " kph");
    }
    public static String forecastCity(String city){

        RestAssured.baseURI = "https://api.weatherapi.com/v1";

        String response = given()
                .queryParam("key", api_key)
                .queryParam("q", city)
                .queryParam("aqi", "no")
                .queryParam("alerts", "no")
                .header("Content-Type", "application/json")
                .when().get("/forecast.json")
                .then()
                .statusCode(200)
                .extract().response().asPrettyString();

        return response;
    }
}
