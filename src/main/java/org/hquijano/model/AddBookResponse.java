package org.hquijano.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBookResponse {
    @JsonProperty("Msg")
    private String msg;
    @JsonProperty("ID")
    private String id;

    public String getMsg(){
        return msg;
    }

    public String getId(){
        return id;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public void setId(String id){
        this.id = id;
    }

    public String toString(){
        return "AddBookResponse{Msg:'" + msg + "', id:'" + id + "'}";
    }
}