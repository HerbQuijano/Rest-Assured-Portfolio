package utils;

public class Payloads {
    public static String addBook(String bookName, String isbn, String aisle, String bookAuthor){
        String postAddBookPayload = "{\n" +
                "  \"name\": \""+ bookName +"\",\n" +
                "  \"isbn\": \"" + isbn + "\",\n" +
                "  \"aisle\": \"" + aisle + "\",\n" +
                "  \"author\": \"" + bookAuthor + "\"\n" +
                "}";

        return postAddBookPayload;
    }

    public static String addUser(String userName, String jobTitle) {
        String postUserPayload =
                "{\n" +
                "    \"\": \"herb\",\n" +
                "    \"job\": \"qa\"\n" +
                "}";

        return postUserPayload;
    }
}
