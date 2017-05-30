package com.example.dto;

/**
 * Created by Morgane TROYSI on 30/05/17.
 */
public class ResultDto {
    Long id;

    String name;

    String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
