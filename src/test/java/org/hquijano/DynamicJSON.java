package org.hquijano;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.AddBook;
import utils.Payloads;

import javax.xml.crypto.Data;
import java.io.File;

import static io.restassured.RestAssured.*;

public class DynamicJSON {
    String bookId;

    @BeforeSuite
    public void setup(){
        String baseURI = "http://216.10.245.166";
        RestAssured.baseURI = baseURI;
    }

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{
                {"1987", "axyz"},
                {"2987", "bxyz"},
                {"3987", "cxyz"}
        };
    }

    @Test(dataProvider = "getData")
    public void addBook(String isbn, String aisle){

        String addResponse = given()
                .header("Content-Type", "application/json")
                .body(Payloads.addBook(isbn, aisle))
                .when().post("/Library/Addbook.php")
                .then()
                .statusCode(200)
                .extract().response().asPrettyString();

        JsonPath addBookResponse = new JsonPath(addResponse);

        String msgResponse = addBookResponse.getString("Msg");
        bookId = addBookResponse.getString("ID");
        System.out.println(bookId);

        Assert.assertEquals(msgResponse, "successfully added");
    }
}
