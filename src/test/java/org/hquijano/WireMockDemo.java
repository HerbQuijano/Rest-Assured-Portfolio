package org.hquijano;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class WireMockDemo {
    static WireMockServer wireMockServer;

    public static void setup(){
        wireMockServer = new WireMockServer(8080);
        wireMockServer.start();

        // Configure WireMock server to mock the API endpoints
        configureFor("localhost", 8080);

        stubFor(get(urlEqualTo("/getCourses"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\n" +
                        "  \"dashboard\": {\n" +
                        "    \"purchaseAmount\": 910,\n" +
                        "    \"website\": \"rahulshettyacademy.com\"\n" +
                        "  },\n" +
                        "  \"courses\": [\n" +
                        "    {\"title\": \"Selenium Python\", \"price\": 50, \"copies\": 6},\n" +
                        "    {\"title\": \"Cypress\", \"price\": 40, \"copies\": 4},\n" +
                        "    {\"title\": \"RPA\", \"price\": 45, \"copies\": 10}\n" +
                        "  ]\n" +
                        "}")));

    }

    public static void teardown() {
        if (wireMockServer != null && wireMockServer.isRunning()){
            wireMockServer.stop();
        }

    }
}
