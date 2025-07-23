package utils;

import io.restassured.path.json.JsonPath;

public class StringToJson {
    public static JsonPath convertStringToJson(String input) {
        JsonPath js = new JsonPath(input);
        return js;
    }
}
