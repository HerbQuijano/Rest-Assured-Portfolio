package utils;

public class Payloads {
    public static String addBook(String isbn, String aisle){
        String postAddBookPayload = "{\n" +
                "  \"name\": \"Playwright cookbook\",\n" +
                "  \"isbn\": \"" + isbn + "\",\n" +
                "  \"aisle\": \"" + aisle + "\",\n" +
                "  \"author\": \"JD Baldwin\"\n" +
                "}";

        return postAddBookPayload;
    }
}
