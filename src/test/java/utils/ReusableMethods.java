package utils;

import io.restassured.path.json.JsonPath;

public class ReusableMethods {
    public static JsonPath convertStringToJson(String input) {
        JsonPath js = new JsonPath(input);
        return js;
    }
}
