package org.hquijano.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GetBookByAuthorResponse {
    @JsonProperty("book_name")
    private String book_title;
    @JsonProperty("isbn")
    private String isbn;
    @JsonProperty("aisle")
    private String aisle;

    public String getBook_title(){
        return book_title;
    }

    public String getIsbn(){
        return isbn;
    }

    public String getAisle(){
        return aisle;
    }

    public void setName(String name){
        this.book_title = name;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setAisle(String aisle){
        this.aisle = aisle;
    }
}
