package org.hquijano.tests.LibraryAPITests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.hquijano.model.*;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LibraryTest extends BaseTest{
    RequestSpecification reqSpec;
    String baseURL = "http://216.10.245.166";
    String bookTitle = "Baila";
    String isbn = "CMD";
    String aisle = "4420";
    String author = "Toti Cetina";
    String id;

    @BeforeSuite
    public void setup(){
        reqSpec = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(baseURL)
                .setBasePath("/Library")
                .build();
    }

    @Test
    public void addBook(){
        AddBookRequest book = new AddBookRequest(bookTitle, isbn, aisle, author);

        AddBookResponse addBookResponse = given(reqSpec)
                .body(book)
                .when().post("/Addbook.php")
                .then().log().all().statusCode(200).extract()
                .as(AddBookResponse.class);

        id = addBookResponse.getId();

        Assert.assertEquals(addBookResponse.getMsg(), "successfully added");
        Assert.assertEquals(addBookResponse.getId(), book.getGeneratedId());
    }


    @Test(dependsOnMethods = "addBook")
    public void getBookByID(){
        GetBookByIDResponse[] book = given(reqSpec)
                .queryParam("ID", id)
                .when().get("/GetBook.php")
                .then().assertThat().statusCode(200)
                .extract().as(GetBookByIDResponse[].class);

        Assert.assertEquals(book[0].getBook_name(), bookTitle);
    }

    @Test(dependsOnMethods = "addBook")
    public void getBookByAuthor(){
        GetBookByAuthorResponse[] books = given(reqSpec)
                .queryParam("AuthorName", author)
                .when().get("/GetBook.php")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .extract().as(GetBookByAuthorResponse[].class);

        Assert.assertTrue(books.length > 0, "No books retrieved");

        for (GetBookByAuthorResponse book : books){
            Assert.assertEquals(book.getBook_title(), bookTitle);
        }
    }

    @Test(dependsOnMethods = "addBook")
    public void deleteBook(){
        DeleteBookRequest book = new DeleteBookRequest(id);

        DeleteBookResponse response = given(reqSpec)
                .body(book).log().all()
                .when().post("/DeleteBook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().as(DeleteBookResponse.class);

        Assert.assertEquals(response.getMsg(), "book is successfully deleted");
    }

}
