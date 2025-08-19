package org.hquijano.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddBookRequest {
    private String name;
    private String isbn;
    private String aisle;
    private String author;

    public AddBookRequest(String name, String isbn, String aisle, String author){
        this.name = name;
        this.isbn = isbn;
        this.aisle =  aisle;
        this.author = author;
    }

    public String getName(){
        return name;
    }

    public String getIsbn(){
        return isbn;
    }

    public String getAisle(){
        return aisle;
    }

    public String getAuthor(){
        return author;
    }

    public String getGeneratedId(){
        return isbn + aisle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIsbn(String isbn){
        this.isbn = isbn;
    }

    public void setAisle(String aisle){
        this.aisle = aisle;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    @Override
    public String toString(){
        return "AddBook{name:'" + name + "', isbn:'" + isbn + "', aisle:'" + aisle + "', author:'" + author + "'}";
    }
}
